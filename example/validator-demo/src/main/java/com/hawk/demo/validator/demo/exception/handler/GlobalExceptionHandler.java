package com.hawk.demo.validator.demo.exception.handler;

import com.hawk.demo.validator.demo.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * 全局异常处理
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 异常解析
     */
    private final GlobalExceptionTranslator translator;

    /**
     * 构造自定义异常解析处理
     * @param translator
     */
    public GlobalExceptionHandler(GlobalExceptionTranslator translator) {
        this.translator = translator;
    }

    /**
     * 通用异常处理，兜底服务器异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> error(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(translator.translate(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Internal Server Error"));
    }

    /**
     * Business exception handler
     *
     * @param e business exception
     * @return error response
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> error(BusinessException e) {
        return ResponseEntity
                .status(e.getHttpCode())
                .body(translator.translate(e));
    }


    /**
     * 参数绑定异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> error(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = fieldError != null ? String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()) : e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(this.translator.translate(HttpStatus.BAD_REQUEST.name(), message));
    }


    /**
     * 参数校验未通过异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> error(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = fieldError != null ? String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()) : e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(this.translator.translate(HttpStatus.BAD_REQUEST.name(), message));
    }

    /**
     * 参数方法不匹配异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> error(MethodArgumentTypeMismatchException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(translator.translate(HttpStatus.BAD_REQUEST.name(), e.getMessage()));
    }

    /**
     * 请求参数不支持异常
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> error(MissingServletRequestParameterException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(translator.translate(HttpStatus.BAD_REQUEST.name(), e.getMessage()));
    }

    /**
     * 请求处理器未找到异常
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> error(NoHandlerFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body(translator.translate(HttpStatus.NOT_FOUND.name(), e.getMessage()));
    }

    /**
     * 请求方法不支持异常
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> error(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .body(translator.translate(HttpStatus.METHOD_NOT_ALLOWED.name(), e.getMessage()));
    }

}
