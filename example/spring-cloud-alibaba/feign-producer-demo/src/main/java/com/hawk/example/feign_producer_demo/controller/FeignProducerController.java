package com.hawk.example.feign_producer_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hawk
 * @package com.hawk.example.feign_demo.controller
 * @desc
 * @date 2021/7/15
 */
@RequestMapping("/hawk/feign_producer")
@RestController
public class FeignProducerController {
    @RequestMapping("/hello")
    public String hello(){
        return "feign producer say hello";
    }
}
