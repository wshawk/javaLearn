package com.example.hawk.redis.demo.annotation;

import com.example.hawk.redis.demo.enums.RedisCacheEnum;
import com.example.hawk.redis.demo.enums.RedisLockEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {
    /**
     * 枚举类
     */
    RedisLockEnum lock();

    /**
     * <p>后缀表达式，为空时候表示不需要后缀表达式，采用 SPEL</p>
     * <p>eg:</p>
     * <p>1.不支持加固定后缀！！！ 目前spEl数据源是来自方法入参，要是需要固定后缀，放置在前缀就好了;</p>
     * <p>2.访问参数 比如 methodName(String userId, String pageId)  ->"#userId+'_'+#pageId";</p>
     * <p>3.访问对象内属性 比如 methodName(UserBO userBO)  ->"#userBO.name";</p>
     * <p>4.访问集合数据 比如 methodName(List<String> list) -> "#list.toString()";</p>
     */
    String suffixExpression() default "";
}
