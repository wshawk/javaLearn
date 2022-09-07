package com.example.hello.spring.boot.starter.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hawk
 * @package PACKAGE_NAME
 * @desc
 * @date 2022/9/7
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {
    @Bean
    public HelloService helloService(){
        return new HelloService();
    }
}
