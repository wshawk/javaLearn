package com.hawk.example;

import com.example.hello.spring.boot.starter.autoconfigure.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example
 * @desc
 * @date 2022/9/7
 */
@RestController
@RequestMapping("/test")
public class Controller {
    @Resource
    HelloService helloService;

    @RequestMapping("/hello")
    public String hello(){
        return helloService.getName() + "say: hello";
    }
}
