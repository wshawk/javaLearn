package com.example.hawk.redis.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Getter
public enum RedisLockEnum implements RedisCacheRule {
    INSERT_LOCK("lock:insert", "插入锁", 10L),
    ;
    /**
     * 分布式锁key
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


    @Override
    public TimeUnit getTtlUnit() {
        return TimeUnit.SECONDS;
    }
}
