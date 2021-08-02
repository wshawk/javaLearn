package com.hawk.example.test_spring_aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author hawk
 * @package com.hawk.example.test_spring_aop.controller
 * @desc
 * @date 2021/8/2
 */
@RestController
@RequestMapping(value = "/hawk/test_spring_aop")
public class TestAopController {

    @RequestMapping(value = "/index")
    public String index(@RequestParam String userName){
        return userName;
    }
}
