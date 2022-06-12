package com.hawk.example.transactional.controller;

import com.hawk.example.transactional.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.transactional.controller
 * @desc
 * @date 2022/1/19
 */
@RequestMapping("/test")
@RestController
public class TestController {
    @Resource
    UserService userService;
    @RequestMapping("/add")
    public void add(){
        userService.add();
    }
}
