package com.example.hawk.redis.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Getter
public enum RedisCacheEnum implements RedisCacheRule {
    /**
     * 用户信息
     */
    USER_INFO("user:info", "用户信息", 3600L, TimeUnit.SECONDS)
    ;
    /**
     * 缓存key
     */
    private final String keyPrefix;
    /**
     * 缓存内容描述
     */
    private final String description;
    /**
     * 过期时间
     */
    private final Long ttl;
    /**
     * 过期时间单位
     */
    private final TimeUnit ttlUnit;
}
