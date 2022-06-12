package com.hawk.example.spring_boot_shiro;

import org.apache.shiro.spring.boot.autoconfigure.ShiroAnnotationProcessorAutoConfiguration;
import org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration;
import org.apache.shiro.spring.boot.autoconfigure.ShiroBeanAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wsHawk
 * @Title: SpringBootShiroApplication
 * @ProjectName example
 * @Description: TODO
 * @since 2021/8/2 22:10
 */
@SpringBootApplication(exclude = {ShiroAnnotationProcessorAutoConfiguration.class, ShiroAutoConfiguration.class, ShiroBeanAutoConfiguration.class})
public class SpringBootShiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootShiroApplication.class);
    }
}
