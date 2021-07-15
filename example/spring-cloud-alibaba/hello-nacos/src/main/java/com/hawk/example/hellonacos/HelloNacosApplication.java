package com.hawk.example.hellonacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hawk
 * @package com.hawk.example.hellonacos
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
