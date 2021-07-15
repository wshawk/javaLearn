package com.hawk.example.feign_producer_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hawk
 * @package com.hawk.example.feign_demo
 * @desc
 * @date 2021/7/15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FeignProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignProducerApplication.class, args);
    }
}
