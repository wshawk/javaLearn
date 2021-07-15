package com.hawk.example.use_redis_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hawk
 * @package com.hawk.example.use_redis_demo
 * @desc
 * @date 2021/7/15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UseRedisDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(UseRedisDemoApplication.class, args);
    }
}
