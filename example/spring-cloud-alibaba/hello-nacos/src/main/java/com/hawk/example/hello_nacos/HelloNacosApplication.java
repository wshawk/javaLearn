package com.hawk.example.hello_nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hawk
 * @package com.hawk.example.hello_nacos
 * @desc
 * @date 2021/7/15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HelloNacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloNacosApplication.class, args);
    }
}
