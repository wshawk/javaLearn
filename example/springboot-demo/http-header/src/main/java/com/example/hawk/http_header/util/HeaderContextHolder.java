package com.example.hawk.http_header.util;

import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author hawk
 * @package com.example.hawk.http_header.util
 * @desc
 * @date 2022/6/13
 */
public class HeaderContextHolder {
    private HeaderContextHolder() {
    }

    private static final ThreadLocal<Map<String, String>> CTX = new ThreadLocal<>();

    public static void setContext(Map<String, String> map) {
        CTX.set(map);
    }

    public static void removeContext() {
        CTX.remove();
    }

    public static String getHeader(String key) {
        if (isEmpty()) {
            return null;
        }
        return CTX.get().get(key);
    }

    public static boolean isEmpty() {
        return CollectionUtils.isEmpty(CTX.get());
    }

}
