package com.example.hawk.http_header.controller;

import com.example.hawk.http_header.service.TestService;
import com.example.hawk.http_header.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hawk
 * @package com.example.hawk.http_header.controller
 * @desc
 * @date 2022/6/12
 */
@RestController
public class HelloController {
    @Autowired
    TestService testService;

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        System.out.println(Thread.currentThread().getName() + "===" + HttpRequestUtil.getHttpHeader(HttpHeaders.AUTHORIZATION).get());
        HttpRequestUtil.setInheritableServletRequestAttributes();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "===" + HttpRequestUtil.getHttpHeader(HttpHeaders.AUTHORIZATION).orElse("null"));
            testService.test();
        }).start();

        return "Hello " + name;
    }
}
