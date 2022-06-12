package com.hawk.example.transactional;

import com.hawk.example.transactional.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hawk
 * @package com.hawk.example.transactional
 * @desc
 * @date 2022/1/19
 */
@SpringBootApplication
@MapperScan(basePackages = "com.hawk.example.transactional.mapper")
public class TransactionalApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionalApplication.class);
    }
}
