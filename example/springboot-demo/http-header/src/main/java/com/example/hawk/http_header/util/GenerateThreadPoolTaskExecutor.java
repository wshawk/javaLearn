package com.example.hawk.http_header.util;

import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author hawk
 * @package com.example.hawk.http_header.util
 * @desc
 * @date 2022/6/13
 */
@Component
public class GenerateThreadPoolTaskExecutor extends ThreadPoolTaskExecutor{

    /**
     *
     */
    private static final int MULTIPLE = 5;
    /**
     *
     */
    private static final int ALIVE_TIMEOUT = 30;
    /**
     * 核心线程数：线程池创建时候初始化的线程数
     */
    private final int corePoolSize = Runtime.getRuntime().availableProcessors();

    /**
     * 最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
     */
    private final int maxPoolSize = Runtime.getRuntime().availableProcessors() * MULTIPLE;

    /**
     * 缓冲队列：用来缓冲执行任务的队列
     */
    private final int queueCapacity = maxPoolSize * MULTIPLE;

    /**
     * 允许线程的空闲时间(单位：秒)：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
     */
    private final int keepAliveSeconds = ALIVE_TIMEOUT;

    /**
     * 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
     */
    private String threadNamePrefix = "inheritable-";

    public GenerateThreadPoolTaskExecutor() {
        generateThreadPoolTaskExecutor(corePoolSize, maxPoolSize, queueCapacity, keepAliveSeconds, threadNamePrefix, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * @param threadNamePrefix 异步方法内部线程名称
     * @param corePoolSize     核心线程数
     * @param maxPoolSize      线程池维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
     * @param queueCapacity    缓存队列
     * @param keepAliveSeconds 允许的空闲时间,当超过了核心线程数之外的线程在空闲时间到达之后会被销毁
     * @return ThreadPoolTaskExecutor
     */
    private ThreadPoolTaskExecutor generateThreadPoolTaskExecutor(int corePoolSize, int maxPoolSize, int queueCapacity, int keepAliveSeconds, String threadNamePrefix, RejectedExecutionHandler rejectedExecutionHandler) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        taskExecutor.setTaskDecorator(runnable -> {
            try {
                RequestAttributes context = RequestContextHolder.currentRequestAttributes();
                Map<String,String> previous = MDC.getCopyOfContextMap();
                return () -> {
                    try {
                        RequestContextHolder.setRequestAttributes(context);
                        MDC.setContextMap(previous);
                        runnable.run();
                    } finally {
                        RequestContextHolder.resetRequestAttributes();
                        MDC.clear();
                    }
                };
            } catch (IllegalStateException e) {
                return runnable;
            }
        });

        /*
         * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略
         * 通常有以下四种策略：
         * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
         * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
         * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         * ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务，自动重复调用 execute() 方法，直到成功
         */
        taskExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);
        taskExecutor.initialize();

        return taskExecutor;
    }

}
