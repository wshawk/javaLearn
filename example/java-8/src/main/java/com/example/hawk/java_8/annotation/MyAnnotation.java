package com.example.hawk.java_8.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hawk
 * @package com.example.hawk.java_8.annotation
 * @desc
 * @date 2021/7/29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited
public @interface MyAnnotation {
    String name() default "";
}
