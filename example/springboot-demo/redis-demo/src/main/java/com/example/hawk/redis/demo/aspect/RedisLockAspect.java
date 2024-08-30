package com.example.hawk.redis.demo.aspect;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.util.StrUtil;
import com.example.hawk.redis.demo.annotation.RedisLock;
import com.example.hawk.redis.demo.enums.RedisLockEnum;
import com.example.hawk.redis.demo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class RedisLockAspect {
    @Resource
    private RedisUtil redisUtil;

    private final ExpressionParser parser = new SpelExpressionParser();

    private final LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    private static ConcurrentLinkedQueue<RedisLockHolder> holderList = new ConcurrentLinkedQueue<>();

    private static final ScheduledExecutorService SCHEDULER = new ScheduledThreadPoolExecutor(1,
            r -> {
                Thread thread = new Thread(r);
                thread.setName("redis-lock-monitor");
                thread.setDaemon(true);
                return thread;
            }
    );

    // redis分布式锁自动续命
    {
        SCHEDULER.scheduleAtFixedRate(this::extend, 0, 2, TimeUnit.SECONDS);
    }

    /**
     * 此处实现了自动加锁和解锁
     * 但是没有考虑和事务结合的情况，事务没结束，不能释放锁
     * 方法没有执行完的情况下，不能让锁过期，应该实现锁的自动续期
     * 后续待完善。
     */
    @Around("@annotation(com.example.hawk.redis.demo.annotation.RedisLock)")
    public Object cached(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = null;
        Object value = null;
        boolean lockResult = false;
        String lockValue = UUID.randomUUID().toString();
        Method method = getMethod(joinPoint);
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        key = getLockKey(redisLock, joinPoint);
        RedisLockEnum lock = redisLock.lock();

        try {
            lockResult = redisUtil.tryLock(key, lockValue, lock.getTtl());
        } catch (Exception e) {
            log.error("获取分布式锁异常：", e);
        }
        if (lockResult) {
            log.info("get lock [{}] success", key);
            // 放到队列中
            holderList.add(new RedisLockHolder(key, lock.getTtl(), System.currentTimeMillis(), Thread.currentThread()));
            // 执行业务逻辑获取结果数据
            value = joinPoint.proceed();
        } else {
            // 此处可以改为更友好的提示返回出去
            throw new RuntimeException("获取分布式锁失败");
        }

        if (redisUtil.unLock(key, lockValue)) {
            log.info("release lock [{}] success", key);
        } else {
            log.info("release lock [{}] failed", key);
        }

        return value;
    }

    private String getLockKey(RedisLock redisLock, ProceedingJoinPoint joinPoint) {
        String suffixExpression = redisLock.suffixExpression();
        String keyPrefix = redisLock.lock().getKeyPrefix();
        String currentThreadName = Thread.currentThread().getName();
        if (StrUtil.isNotBlank(suffixExpression)) {
            Object parseSPEL = parseSPEL(getMethod(joinPoint), joinPoint.getArgs(), suffixExpression);
            return StrUtil.join(StrUtil.COLON, keyPrefix, parseSPEL, currentThreadName);
        }
        return StrUtil.join(StrUtil.COLON, keyPrefix, currentThreadName);
    }

    private Object parseSPEL(Method method, Object[] arguments, String spel) {
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], arguments[len]);
        }
        try {
            Expression expression = parser.parseExpression(spel);
            return expression.getValue(context);
        } catch (Exception e) {
            return "";
        }
    }

    private static Method getMethod(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = pjp
                        .getTarget()
                        .getClass()
                        .getDeclaredMethod(pjp.getSignature().getName(),
                                method.getParameterTypes());
            } catch (SecurityException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        return method;
    }

    private void extend() {
        Iterator<RedisLockHolder> iterator = holderList.iterator();
        while (iterator.hasNext()) {
            RedisLockHolder redisLockHolder = iterator.next();
            if (Objects.isNull(redisLockHolder)) {
                iterator.remove();
                continue;
            }

            if (redisUtil.get(redisLockHolder.getLockKey()) == null) {
                iterator.remove();
                continue;

            }
            long currentTime = System.currentTimeMillis();
            boolean shouldExtend = (redisLockHolder.getLastUpdateTime() + redisLockHolder.getUpdatePeriod()) <= currentTime;
            if (shouldExtend) {
                redisLockHolder.setLastUpdateTime(currentTime);
                redisUtil.expire(redisLockHolder.getLockKey(), redisLockHolder.getLockTime(), TimeUnit.SECONDS);
                log.info("lock key[{}] has been extended", redisLockHolder.getLockKey());
            }
        }
    }
}

