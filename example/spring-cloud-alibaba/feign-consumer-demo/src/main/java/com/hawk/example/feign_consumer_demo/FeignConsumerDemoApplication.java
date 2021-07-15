package com.hawk.example.feign_consumer_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author hawk
 * @package com.hawk.example.feign_consumer_demo
 * @desc
 * @date 2021/7/15
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.hawk.example")
public class FeignConsumerDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerDemoApplication.class, args);
    }
}
