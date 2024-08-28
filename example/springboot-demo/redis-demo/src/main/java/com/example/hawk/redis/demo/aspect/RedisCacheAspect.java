package com.example.hawk.redis.demo.aspect;

import cn.hutool.core.util.StrUtil;
import com.example.hawk.redis.demo.annotation.RedisCache;
import com.example.hawk.redis.demo.enums.RedisCacheEnum;
import com.example.hawk.redis.demo.util.JsonUtil;
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
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class RedisCacheAspect {
    @Resource
    private RedisUtil redisUtil;

    private final ExpressionParser parser = new SpelExpressionParser();

    private final LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Around("@annotation(com.example.hawk.redis.demo.annotation.RedisCache)")
    public Object cached(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = null;
        Object value = null;
        try {
            Method method = getMethod(joinPoint);
            RedisCache redisCache = method.getAnnotation(RedisCache.class);
            Class<?> returnType = method.getReturnType();
            key = getCacheKey(redisCache, joinPoint);

            // 从缓存获取数据
            value = redisUtil.get(key);
            if (null != value) {
                log.info("get redis cache content ：" + value);
                return value;
            } else {
                log.info("redis cache miss, key:[]" + key);
            }
            //执行业务逻辑获取结果数据
            value = joinPoint.proceed();

            RedisCacheEnum redisCacheEnum = redisCache.cacheEnum();
            if (value != null) {
                if (redisCacheEnum.getTtl() <= 0) {
                    // 如果没有设置过期时间,则无限期缓存
                    redisUtil.set(key, JsonUtil.serialize(value));
                } else {
                    // 否则设置缓存时间
                    redisUtil.set(key, value.toString(), redisCacheEnum.getTtl(), redisCacheEnum.getTtlUnit());
                }
            } else {
                // 处理问题数据拿不到情况
                redisUtil.set(key, JsonUtil.serialize(returnType.getDeclaredConstructor().newInstance()),
                        10L, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error("从缓存获取数据异常：", e);
        }
        return value;
    }

    private String getCacheKey(RedisCache redisCache, ProceedingJoinPoint joinPoint) {
        String suffixExpression = redisCache.suffixExpression();
        String keyPrefix = redisCache.cacheEnum().getKeyPrefix();
        if (StrUtil.isNotBlank(suffixExpression)){
            return StrUtil.join(StrUtil.COLON, keyPrefix, parseSPEL(getMethod(joinPoint), joinPoint.getArgs(), suffixExpression));
        }
        return keyPrefix;
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
}