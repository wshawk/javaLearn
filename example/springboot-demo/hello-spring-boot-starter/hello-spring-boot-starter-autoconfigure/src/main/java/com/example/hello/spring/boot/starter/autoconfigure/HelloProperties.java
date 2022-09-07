package com.example.hello.spring.boot.starter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hawk
 * @package PACKAGE_NAME
 * @desc
 * @date 2022/9/7
 */
@ConfigurationProperties(prefix = "hawk")
public class HelloProperties {
    private String name;

    public HelloProperties(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
