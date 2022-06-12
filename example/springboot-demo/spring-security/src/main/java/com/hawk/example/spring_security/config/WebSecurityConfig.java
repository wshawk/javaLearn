package com.hawk.example.spring_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author hawk
 * @package com.hawk.example.spring_security_start.config
 * @desc
 * @date 2021/10/27
 */
@Configuration
@EnableWebSecurity
// 启动基于方法注解的控制
@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 配置认证管理器
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        // 配置 userDetailsService ,这是用来查询用户信息的
        auth.userDetailsService(userDetailsService);
        // 设置擦除密码，如果设置成false，那么在spring security中流转的用户认证信息都会带有密码，一般我们都会擦除
        auth.eraseCredentials(true);
        super.configure(auth);
    }


    /**
     * 配置url安全认证拦截的
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * csrf().disable()   关闭跨域请求限制，如果开启，可能会出现很多非get请求出现405
         * .authorizeRequests()  启动请求认证
         * .antMatchers("/**").authenticated()  匹配指定的url地址进行认证判断
         * .anyRequest().permitAll()    对其他地址进行放行
         * formLogin()   启动表单登录
         * .loginPage("/login.html")    可以自定义登录页面
         * .failureUrl("/login_fail.html")   表单登录失败的跳转地址
         * .defaultSuccessUrl("/login_s.html",true)   表单登录成功的跳转地址，
         *           参数2如果为false，登录成功时会跳转到拦截前的页面，true时登录成功固定跳转给定的页面
         * .logout()   启动用户退出，security提供了默认退出地址：/logout
         * .logoutSuccessUrl("/logout.html")   成功推出后的跳转地址
         * */
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAnyRole("role2")
                .antMatchers("/r/r3").access("hasAnyAuthority('p1') and hasAuthority('p2')")
                .antMatchers("/r/**").authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .failureHandler(getFailureHandler())
                .successHandler(getSuccessHandler())
                // .failureUrl("/login_fail.html")
                // .defaultSuccessUrl("/login_s.html",true)
                .permitAll()// permitAll表示登录相关的请求都放开，一定要加，不然你连登录页面都看不到
                .and()
                .logout()
                .logoutSuccessUrl("/logout.html")
                .permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(AjaxAuthenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());// 这里也要加，不然你退出后就看不到退出页面了
    }
    /**
     * 这个是配置密码编码器的<br/>
     * NoOpPasswordEncoder表示不进行密码编码
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 建议使用
        return new BCryptPasswordEncoder();
    }

    /**
     * 这个是我自己写来加密密码的
     * */
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.err.println(passwordEncoder.encode("123"));
    }


    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    /**
     * 这个必须重写，才能使用AuthenticationManager，在成员变量注入进来，再注入过滤器中
     * */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // ********************************************************************
        // 这里必须自己创建ProviderManager，并且把两个provider传进去，否则会一直创建新的ProviderManager
        // 然后不断在自己和parentAuthenticationManager之间调用，最后栈溢出。
        // 网上很多博客都是说在configure(AuthenticationManagerBuilder auth)方法中设置，我实际上测试在这个方法中设置是没用的
        return new ProviderManager(Collections.singletonList(daoAuthenticationProvider()
        ));
    }
    /**
     * DaoAuthenticationProvider是给UsernamePasswordAuthenticationFilter认证用的
     * */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * 认证失败的处理器
     * */
    @Bean
    public AuthenticationFailureHandler getFailureHandler(){
        return (httpServletRequest, httpServletResponse, e) -> {
            /**
             * 认证失败的处理器
             * 默认情况下，认证成功/失败，都是重定向到一个页面上的。
             * 如果想要认证成功/失败后，返回信息体，而不是重定向，就必须用AuthenticationHandler
             * 另外，绝对不要用httpServletResponse.getWriter()，一旦你用了Writer，那么setContentType("text/plain;charset=UTF-8")设置的
             * 编码utf-8就永远不会被设置进去。具体原因自己看org.apache.catalina.connector.Response#setContentType(java.lang.String)376行
             * */
            httpServletResponse.getOutputStream().write(e.getMessage().getBytes(StandardCharsets.UTF_8));
            httpServletResponse.setContentType("text/plain;charset=UTF-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.flushBuffer();
        };
    }

    /**
     * 认证成功的处理器，和上面一样
     * */
    @Bean
    public AuthenticationSuccessHandler getSuccessHandler(){
        return (httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.getOutputStream().write("登录成功".getBytes(StandardCharsets.UTF_8));
            httpServletResponse.setContentType("text/plain;charset=UTF-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.flushBuffer();
        };
    }
    /**
     * 一般访问资源时，如果没有权限，就会返回内置异常信息，如需要返回自定义信息，需要重写AccessDeniedHandler
     * 并需要在void configure(HttpSecurity http)方法中加
     *   .and()
     *   .exceptionHandling()
     *   .accessDeniedHandler( accessDeniedHandler());               // 访问拒绝时处理器，返回自定义信息
     * */
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return (httpServletRequest, httpServletResponse, e) -> {
            HashMap<String, Object> responseBody = new HashMap<>(4);

            responseBody.put("status","403");
            responseBody.put("msg",e.getMessage());
            responseBody.put("data",null);
            responseBody.put("list",null);
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().write(responseBody.toString());
        };
    }


    /**
     * 一般访问资源时，如果没有登录认证，就会跳转到登录页，重写AuthenticationEntryPoint返回自定义信息
     * 需要在void configure(HttpSecurity http)方法中加
     *   .and()
     *   .exceptionHandling()
     *   .authenticationEntryPoint(AjaxAuthenticationEntryPoint())   // 未登录时的处理器，返回自定义信息，而不是跳转登录页面
     * */
    @Bean
    public AuthenticationEntryPoint AjaxAuthenticationEntryPoint(){
        return (httpServletRequest, httpServletResponse, e) -> {
            HashMap<String, Object> responseBody = new HashMap<>(4);

            responseBody.put("status","401");
            responseBody.put("msg","Need Authorities!");
            responseBody.put("data",null);
            responseBody.put("list",null);
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().write(responseBody.toString());
        };
    }


}

