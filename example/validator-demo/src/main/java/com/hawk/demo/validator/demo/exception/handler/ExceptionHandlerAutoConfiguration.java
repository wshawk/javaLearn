package com.hawk.demo.validator.demo.exception.handler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 统一异常处理.
 */
@Configuration
public class ExceptionHandlerAutoConfiguration {

    /**
     * 全局异常解析
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(GlobalExceptionTranslator.class)
    public GlobalExceptionTranslator defaultGlobalExceptionTranslator() {
        return new GlobalExceptionTranslator();
    }

    /**
     * 全局异常处理
     * @param globalExceptionTranslator 异常处理
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler(GlobalExceptionTranslator globalExceptionTranslator) {
        return new GlobalExceptionHandler(globalExceptionTranslator);
    }

    /**
     * 全局响应处理
     * @param globalExceptionTranslator 异常解析
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(CommonResponseAdvice.class)
    public CommonResponseAdvice commonResponseAdvice(GlobalExceptionTranslator globalExceptionTranslator) {
        return new CommonResponseAdvice(globalExceptionTranslator);
    }
}
