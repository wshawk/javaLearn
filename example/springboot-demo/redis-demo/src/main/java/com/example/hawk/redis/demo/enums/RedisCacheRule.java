package com.example.hawk.redis.demo.enums;

import java.util.concurrent.TimeUnit;

public interface RedisCacheRule {
    String getKeyPrefix();
    String getTtl();
    TimeUnit getTtlUnit();
}
