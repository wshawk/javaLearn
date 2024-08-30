package com.example.hawk.redis.demo.enums;

import java.util.concurrent.TimeUnit;

public interface RedisCacheRule {
    String getKeyPrefix();
    Long getTtl();
    TimeUnit getTtlUnit();
}
