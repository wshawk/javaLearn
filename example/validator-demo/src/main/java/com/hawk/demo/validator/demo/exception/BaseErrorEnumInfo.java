package com.hawk.demo.validator.demo.exception;

public enum BaseErrorEnumInfo implements ErrorInfo {
    SUCCESS("0", "SUCCESS"),
    FAIL("1", "FAIL"),


    ;

    private final String code;
    private final String msg;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    BaseErrorEnumInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
