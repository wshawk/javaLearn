package com.example.tkmybatisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.example.tkmybatisdemo.dao")
@SpringBootApplication
public class TkMybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkMybatisDemoApplication.class, args);
    }

}
