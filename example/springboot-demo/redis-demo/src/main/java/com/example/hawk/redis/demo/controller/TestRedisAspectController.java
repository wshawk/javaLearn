package com.example.hawk.redis.demo.controller;

import com.example.hawk.redis.demo.annotation.RedisCache;
import com.example.hawk.redis.demo.annotation.RedisLock;
import com.example.hawk.redis.demo.enums.RedisCacheEnum;
import com.example.hawk.redis.demo.enums.RedisLockEnum;
import com.example.hawk.redis.demo.util.JsonUtil;
import com.example.hawk.redis.demo.util.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestRedisAspectController {
    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/userInfo")
    @RedisCache(cacheEnum = RedisCacheEnum.USER_INFO, suffixExpression = "#id")
    public String getUserInfo(@RequestParam Integer id) {
        UserInfo userInfo = UserInfo.builder()
                .name("张三" + id)
                .age(id)
                .address("北京")
                .build();

        return JsonUtil.serialize(userInfo);
    }

    @GetMapping("/clear")
    public void clearCache() {
        redisUtil.delete(RedisCacheEnum.USER_INFO.getKeyPrefix());
    }


    @PostMapping("/add/userInfo")
    @RedisLock(lock = RedisLockEnum.INSERT_LOCK, suffixExpression = "#userInfo.name + '_' + #userInfo.age")
    @SneakyThrows
    public String addUserInfo(@RequestBody UserInfo userInfo) {
        Thread.sleep(15000);
        return JsonUtil.serialize(userInfo);
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class UserInfo {
        private String name;
        private Integer age;
        private String address;
    }

}
