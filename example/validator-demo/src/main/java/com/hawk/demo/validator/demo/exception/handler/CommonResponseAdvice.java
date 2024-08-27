package com.hawk.demo.validator.demo.exception.handler;

import com.hawk.demo.validator.demo.model.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Optional;

@RestControllerAdvice
public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {

    @Value(value = "${common-web.translate.path:com.hawk}")
    private String responseTranslatePath;

    private final GlobalExceptionTranslator exceptionTranslator;

    public CommonResponseAdvice(GlobalExceptionTranslator exceptionTranslator) {
        this.exceptionTranslator = exceptionTranslator;
    }

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Class<? extends HttpMessageConverter<?>> aClass) {
        return Optional.of(methodParameter)
                .map(MethodParameter::getDeclaringClass)
                .map(Class::getName)
                .map(name -> name.startsWith(responseTranslatePath))
                .orElse(false);
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter methodParameter, @NonNull MediaType mediaType,
            @NonNull Class<? extends HttpMessageConverter<?>> aClass, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (body instanceof R) {
            return body;
        }
        return this.exceptionTranslator.translateSuccess(body);
    }
}
