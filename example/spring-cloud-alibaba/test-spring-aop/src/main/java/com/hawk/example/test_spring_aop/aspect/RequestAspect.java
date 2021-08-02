package com.hawk.example.test_spring_aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author hawk
 * @package com.hawk.example.test_spring_aop.aspect
 * @desc 切面打印日志
 * @date 2021/8/2
 */
@Aspect
@Component
public class RequestAspect {
    Logger logger= LoggerFactory.getLogger(getClass());

    @Before(value = "within(com.hawk.example.test_spring_aop.controller.*)")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] params = methodSignature.getParameterNames();
        Method method = methodSignature.getMethod();
        StringBuilder request = new StringBuilder();
        for (int i=0; i<params.length; i++){
            request.append(params[i] + " = " + args[i]);
        }
        logger.info("{} 执行 {} 方法, 请求参数: {}",
                method.getDeclaringClass().getName(),
                method.getName(),
                request);
    }

    @AfterReturning(value = "within(com.hawk.example.test_spring_aop.controller.*)", returning = "result")
    public void after(JoinPoint joinPoint, Object result){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        logger.info("{} 执行 {} 方法, 返回结果: {}",
                method.getDeclaringClass().getName(),
                method.getName(),
                result.toString());
    }
}
