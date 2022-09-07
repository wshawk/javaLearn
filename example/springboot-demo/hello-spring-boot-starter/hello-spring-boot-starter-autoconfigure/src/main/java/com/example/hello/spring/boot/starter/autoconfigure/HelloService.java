package com.example.hello.spring.boot.starter.autoconfigure;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package PACKAGE_NAME
 * @desc
 * @date 2022/9/7
 */
public class HelloService {
    @Resource
    HelloProperties helloProperties;

    public String getName(){
        return helloProperties.getName();
    }
}
