package com.example.hawk.redis.demo.delay;

import com.example.hawk.redis.demo.util.CheckFun;
import com.example.hawk.redis.demo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author hawk
 * @package com.example.hawk.redis.demo.delay
 * @desc
 * @date 2022/7/5
 */
@Slf4j
public abstract class AbstractDelayQueueFactory {


    @Resource
    RedisUtil redisUtil;

    //默认休眠时间
    public static final long SLEEP_TIME = 5L;

    /**
     * 添加任务
     *
     * @param jobId
     * @param timeScore
     * @return
     */
    public Boolean addJobId(String jobId, Long timeScore) {
        return redisUtil.zAdd(getDelayQueueName(), jobId, timeScore);
    }

    /**
     * 删除任务
     *
     * @param jobId
     * @return
     */
    public void delJobId(String jobId) {
        redisUtil.zRemove(getDelayQueueName(), jobId);
    }

    /**
     * 查询任务 score
     *
     * @param jobId
     * @return
     */
    public Double queryJobId(String jobId) {
        return redisUtil.zScore(getDelayQueueName(), jobId);
    }

    /**
     * 开始跑延时队列
     */
    private void startDelayQueue() {
        log.info("延时队列开始运作.......");
        while (true) {
            //获取任务列表
            //如果有两个同样QueueName的队列同时execute，可能拿到同一个资源,这时的jobIds就成为了共享资源
            //可能会有并发问题导致一个订单被回退多次。内部进行回退的时候应该要加锁吧。回退前应该也要判断是否已经被回退过了。
            Set<String> jobIds = redisUtil.zRangeByScore(getDelayQueueName(), 0, System.currentTimeMillis());
            log.info("====jobIds:{}", jobIds);
            if (jobIds == null || jobIds.size() == 0) {
                //休眠五秒
                CheckFun.Consumer.tryOf(TimeUnit.SECONDS::sleep, getSleepTime());
                continue;
            }
            jobIds.stream()
                    //1.留下成功刪除redis的jobId
                    .filter(x -> {
                        Long zRemove = redisUtil.zRemove(getDelayQueueName(), x);
                        log.info("移除结果：zRemove:{}", zRemove);
                        return zRemove > 0;
                    })
                    //2.执行方法execute，如果抛出异常则执行异常处理方法(将异常的jobId放进到回退异常队列里)
                    .forEach(s -> CheckFun.Function.tryOf(a -> this.execute(s), s, c -> this.errorExecute(s), s)
                            //3.执行方法execute没报错，返回的是false的
                            .filter(b -> !b)
                            //4.将execute返回是false的jobId 继续执行异常处理方法(将false的jobId放进到回退异常队列里)
                            .ifPresent(c -> this.errorExecute(s)));

        }
    }


    /**
     * 设置延时队列名字 zset key
     *
     * @return 延时队列名字
     */
    public abstract String getDelayQueueName();

    /**
     * 最终执行任务的方法
     *
     * @param jobId 任务id
     * @return 执行结果
     */
    public abstract Boolean execute(String jobId);

    private Boolean errorExecute(String jobId) {
        //将失败任务放入一个失败队列,启动定时任务去轮询失败队列,并再次进行库存回退(多久执行一次？执行多少次?)

        return CheckFun.Function.tryOf(a -> redisUtil.zAdd(getDelayQueueName() + ":error", a, System.currentTimeMillis()), jobId).orElse(false);
    }


    @PostConstruct
    public void init() {
        // TODO:队列数量上来之后可以考虑用线程池处理 但注意并发处理避免浪费资源
        new Thread(this::startDelayQueue).start();
        new Thread(this::startDelayQueue).start();
        new Thread(this::startDelayQueue).start();
    }


    /**
     * 休眠时间
     * @return
     */
    protected Long getSleepTime(){
        return SLEEP_TIME;
    }


}

