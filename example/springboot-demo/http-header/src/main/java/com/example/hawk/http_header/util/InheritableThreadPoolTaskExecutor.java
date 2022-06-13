package com.example.hawk.http_header.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author hawk
 * @package com.example.hawk.http_header.util
 * @desc
 * @date 2022/6/13
 */
@Component
public class InheritableThreadPoolTaskExecutor extends GenerateThreadPoolTaskExecutor{

    @Override
    public void execute(Runnable task) {
        super.execute(task);
    }
    @Override
    public void execute(Runnable task, long startTimeout) {
        super.execute(task, startTimeout);
    }
    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(task);
    }
}
