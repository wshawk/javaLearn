package com.hawk.demo.validator.demo.model;

import com.hawk.demo.validator.demo.exception.ErrorInfo;
import lombok.Data;

import static com.hawk.demo.validator.demo.exception.BaseErrorEnumInfo.FAIL;
import static com.hawk.demo.validator.demo.exception.BaseErrorEnumInfo.SUCCESS;

@Data
public class R<T> {
    private String code;
    private String msg;
    private T data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public R(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public R() {
    }

    public static <T> R<T> success(T data, String msg) {
        return new R<>(SUCCESS.getCode(), msg, data);
    }

    public static <T> R<T> success(T data) {
        return new R<>(SUCCESS.getCode(), SUCCESS.getMsg(), data);
    }

    public static R<Void> success() {
        return new R<>(SUCCESS.getCode(), SUCCESS.getMsg(), null);
    }

    public static R<Void> fail() {
        return new R<>(FAIL.getCode(), FAIL.getMsg(), null);
    }

    public static <T> R<T> fail(String msg) {
        return new R<>(FAIL.getCode(), msg, null);
    }

    public static <T> R<T> fail(ErrorInfo errorInfo) {
        return new R<>(errorInfo.getCode(), errorInfo.getMsg(), null);
    }

    public static <T> R<T> fail(String code, String message) {
        return new R<T>(code, message, null);
    }
}
