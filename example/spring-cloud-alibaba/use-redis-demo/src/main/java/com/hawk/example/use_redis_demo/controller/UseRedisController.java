package com.hawk.example.use_redis_demo.controller;

import com.hawk.example.use_redis_demo.utils.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.use_redis_demo.controller
 * @desc
 * @date 2021/7/15
 */
@RestController
@RequestMapping("/hawk/use_redis")
public class UseRedisController {
    @Resource
    RedisUtil redisUtil;

    @RequestMapping("/get")
    public String get(){
        return redisUtil.get("hawk");
    }
}
