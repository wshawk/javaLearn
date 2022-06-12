package com.example.hawk.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hawk
 * @package com.example.hawk.logback.controller
 * @desc
 * @date 2022/6/12
 */
@RestController
@Slf4j
public class HelloController {
    @RequestMapping("/hello")
    public String test(){
        log.info("========================================xxxxxx========================================");

        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadPool.execute(() ->{
            throw new RuntimeException("execute throw a exception");
        });
        return "hello";
    }
}
