package com.example.hawk.http_header.controller;

import com.example.hawk.http_header.service.TestService;
import com.example.hawk.http_header.service.TestServiceB;
import com.example.hawk.http_header.util.HttpRequestUtil;
import com.example.hawk.http_header.util.InheritableThreadPoolTaskExecutor;
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

        testService.test();

        return "Hello " + name;
    }
}
