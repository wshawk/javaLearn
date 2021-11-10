package com.hawk.example.shardingsphere_jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hawk
 * @package com.hawk.example.shardingsphere_jdbc
 * @desc
 * @date 2021/11/10
 */
@SpringBootApplication
@MapperScan("com.hawk.example.shardingsphere_jdbc.mapper")
public class ShardingsphereJdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereJdbcApplication.class);
    }
}
