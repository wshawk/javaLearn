package com.hawk.demo.validator.demo.exception;

public class BusinessException extends RuntimeException {

    /**
     * http响应状态码
     */
    private final int httpCode;

    /**
     * 响应code, error
     */
    private final String code;

    public BusinessException(int httpCode, String code, String message) {
        super(message);
        this.httpCode = httpCode;
        this.code = code;
    }

    public BusinessException(int httpCode, ErrorInfo baseResultType) {
        super(baseResultType.getMsg());
        this.httpCode = httpCode;
        this.code = baseResultType.getCode();
    }

    public BusinessException(ErrorInfo baseResultType) {
        super(baseResultType.getMsg());
        this.httpCode = 500;
        this.code = baseResultType.getCode();
    }

    public BusinessException(int httpCode, String code, String message, Throwable cause) {
        super(message, cause);
        this.httpCode = httpCode;
        this.code = code;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.httpCode = 500;
        this.code = code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getCode() {
        return code;
    }
}
