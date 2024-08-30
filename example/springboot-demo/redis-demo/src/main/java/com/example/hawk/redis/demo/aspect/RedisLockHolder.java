package com.example.hawk.redis.demo.aspect;

import lombok.Data;

@Data
public class RedisLockHolder {
    /**
     * 加锁的key
     */
    private String lockKey;
    /**
     * 加锁时间。秒
     */
    private Long lockTime;
    /**
     * 上次更新时间 ms
     */
    private Long lastUpdateTime;
    /**
     * 当前线程
     */
    private Thread currentThread;
    /**
     * 更新的时间周期 ms 公式 - 加锁时间(转成ms) / 3
     */
    private Long updatePeriod;

    public RedisLockHolder(String lockKey, Long lockTime, Long lastUpdateTime, Thread currentThread) {
        this.lockKey = lockKey;
        this.lockTime = lockTime;
        this.lastUpdateTime = lastUpdateTime;
        this.currentThread = currentThread;
        this.updatePeriod = lockTime * 1000 / 3;
    }
}
