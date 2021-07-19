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

    @RequestMapping("/get_lock")
    public String getLock() {
        // 获取分布式锁
        Boolean getLock = redisUtil.getLock("hawk", "lock", 9000);
        if (getLock) {
            // 释放锁
            redisUtil.releaseLock("hawk", "lock");
            if (redisUtil.getLock("hawk", "lock", 2000)){
                return "get lock success then release lock and then get lock success";
            }else{
                return "get lock success then release lock and then get lock fail";
            }
        } else {
            return "get lock fail";
        }
    }
}
