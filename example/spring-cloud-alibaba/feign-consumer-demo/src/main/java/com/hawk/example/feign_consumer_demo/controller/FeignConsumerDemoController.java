package com.hawk.example.feign_consumer_demo.controller;

import com.hawk.example.feign_producer_demo.feign.FeignProducerProviderFeign;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.feign_consumer_demo.controller
 * @desc
 * @date 2021/7/15
 */
@RestController
@RequestMapping("/hawk/feign_consumer")
public class FeignConsumerDemoController {
    @Resource
    FeignProducerProviderFeign feignProducerProviderFeign;

    @RequestMapping("/hello")
    public String hello(){
        return feignProducerProviderFeign.hello();
    }
}
