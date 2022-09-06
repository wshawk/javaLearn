package com.example.hawk.redis.demo.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author hawk
 * @package com.example.hawk.redis.demo.delay
 * @desc
 * @date 2022/7/5
 */
@Component
@Slf4j
public class OrderCancelDelayQueue extends AbstractDelayQueueFactory {
    @Override
    public String getDelayQueueName() {
        return "hawk_pay:cancel:order";
    }

    @Override
    public Boolean execute(String jobId) {
        log.info("============================执行延时任务jobId：{}", jobId);
        return true;
    }
}
