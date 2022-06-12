package com.hawk.example.transactional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author hawk
 * @package com.hawk.example.transactional
 * @desc
 * @date 2022/1/18
 */
@Aspect
@Component
public class AopTransaction {
    @Resource
    private TransactionUtil transactionUtil;

    private TransactionStatus transactionStatus;

    /**
     * 环绕通知 在方法之前和之后处理事情
     *
     * @param pjp 切入点
     */
    @Around("execution(* com.hawk.example.transactional.service.*.*(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        // 1.获取方法的注解
        MyAnnotation annotation = this.getMethodMyAnnotation(pjp);
        // 2.判断是否需要开启事务
        transactionStatus = begin(annotation);
        // 3.调用目标代理对象方法
        pjp.proceed();
        // 4.判断关闭事务
        commit(transactionStatus);
    }

    /**
     * 获取代理方法上的事务注解
     *
     * @param pjp 切入点
     */
    private MyAnnotation getMethodMyAnnotation(ProceedingJoinPoint pjp) throws Exception {
        //1. 获取代理对对象的方法
        String methodName = pjp.getSignature().getName();
        //2. 获取目标对象
        Class<?> classTarget = pjp.getTarget().getClass();
        //3. 获取目标对象类型
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        //4. 获取目标对象方法
        Method objMethod = classTarget.getMethod(methodName, par);
        //5. 获取该方法上的事务注解
        MyAnnotation annotation = objMethod.getDeclaredAnnotation(MyAnnotation.class);
        return annotation;
    }

    /**
     * 开启事务
     */
    private TransactionStatus begin(MyAnnotation annotation) {
        if (annotation == null) return null;
        return transactionUtil.begin();
    }

    /**
     * 关闭事务
     */
    private void commit(TransactionStatus transactionStatus) {
        if (transactionStatus != null) transactionUtil.commit(transactionStatus);
    }

    /**
     * 异常通知进行 回滚事务
     */
    @AfterThrowing("execution(* com.hawk.example.transactional.service.*.*(..))")
    public void afterThrowing() {
        // 获取当前事务 直接回滚
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        if (transactionStatus != null) transactionUtil.rollback(transactionStatus);
    }
}
