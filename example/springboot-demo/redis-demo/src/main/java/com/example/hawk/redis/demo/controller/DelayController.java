package com.example.hawk.redis.demo.controller;

import com.example.hawk.redis.demo.delay.OrderCancelDelayQueue;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.example.hawk.redis.demo.controller
 * @desc
 * @date 2022/7/5
 */
@Slf4j
@RestController
public class DelayController {

    @Resource
    OrderCancelDelayQueue orderCancelDelayQueue;

    @RequestMapping("/add")
    public void add(@RequestParam("time") Long time, @RequestParam("jobId") String jobId){
        log.info("增加定时任务，jobId:{}, time:{}", jobId, time);
        orderCancelDelayQueue.addJobId(jobId, time);
    }
}
