package com.hawk.example.spring_security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hawk
 * @package com.hawk.example.spring_security_start
 * @desc
 * @date 2021/10/25
 */
@RestController
public class HelloSecurity {

    @GetMapping("/hello")
    public String hello() {
        return "hello Security, don't need login";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin page";
    }

    @GetMapping("/logined")
    public String logined(){
        return "logined page";
    }

    @RequestMapping("/fail")
    public String fail(){
        return "fail";
    }

    @RequestMapping("/login_s")
    public String loginS(){
        return "login_s";
    }

    @RequestMapping("/r/r1")
    public String rR1(){
        return "hasAuthority p1";
    }

    @RequestMapping("/r/r2")
    public String rR2(){
        return "hasAnyRole p2";
    }

    @RequestMapping("/r/r3")
    public String rR3(){
        return "hasAnyAuthority('p1') and hasAuthority('p2')";
    }

    @RequestMapping("/r/t")
    public String rRAll(){
        return "/r/t";
    }

    @PreAuthorize("hasAnyRole('role3')")
    @RequestMapping("/t/t")
    public String tT(){
        return "只有 role3 角色才可以访问";
    }
}
