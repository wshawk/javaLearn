package com.hawk.demo.validator.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Repeatable(EnumValid.List.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint (validatedBy = { IntegerEnumValidator.class, StringEnumValidator.class, ListStringEnumValidator.class })
public @interface EnumValid {
    /**
     * 提示信息
     * @return
     */
    String message() default "Invalid enum value";

    /**
     * 枚举类
     * @return
     */
    Class<? extends Enum<?>> enumClass();

    /**
     * 校验分组
     * @return
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 获取枚举code的方法名 <br>
     * 默认为 getType
     * @return
     */
    String method() default "getType";

    /**
     * 默认必填
     * @return
     */
    boolean required() default true;

    @Documented
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List
    {
        EnumValid[] value();
    }
}
