package com.hawk.example.hello_nacos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hawk
 * @package com.hawk.example.hello_nacos.controller
 * @desc
 * @date 2021/7/15
 */
@RestController
@RequestMapping("/hawk/hello")
public class HelloController {
    @RequestMapping("/1")
    public String hello(){
        return "hello";
    }
}
