package com.hawk.demo.validator.demo.exception.handler;

import com.hawk.demo.validator.demo.exception.BusinessException;
import com.hawk.demo.validator.demo.exception.ErrorInfo;
import com.hawk.demo.validator.demo.model.R;

/**
 * Translate exception into error response
 * <p>
 * It's injected into {@link GlobalExceptionHandler} as a spring bean.
 * So it must be extended by a subclass which declared as a spring bean.
 *
 * @author xujin125
 * @since 2022-06-30
 */
public class GlobalExceptionTranslator {

    /**
     * 响应失败
     * @param result 错误响应体
     * @return
     */
    public Object translate(ErrorInfo result) {
        return R.fail(result);
    }

    /**
     * 响应失败
     * @param code 错误编码
     * @param message 消息
     * @return
     */
    public Object translate(String code, String message) {
        return R.fail(code, message);
    }

    /**
     * 响应失败
     */
    public Object translate(BusinessException businessException) {
        return R.fail(businessException.getCode(), businessException.getMessage());
    }
    /**
     * 响应成功结果集
     * @param body 响应结果
     * @return
     */
    public Object translateSuccess(Object body) {
        return R.success(body);
    }
}
