package com.hawk.example.use_log4j2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hawk
 * @package com.hawk.example.use_log4j2
 * @desc
 * @date 2021/7/15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UseLog4j2Application {
    public static void main(String[] args) {
        SpringApplication.run(UseLog4j2Application.class, args);
    }
}
