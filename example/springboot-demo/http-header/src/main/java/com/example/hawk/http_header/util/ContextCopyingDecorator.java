package com.example.hawk.http_header.util;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.AbstractCollection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hawk
 * @package com.example.hawk.http_header.util
 * @desc
 * @date 2022/6/13
 */
public class ContextCopyingDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(@NonNull Runnable runnable) {
        //组装 header map
        Map<String, String> map = wrapHeaderMap();
        return () -> {
            try {
                HeaderContextHolder.setContext(map);
                runnable.run();
            } finally {
                HeaderContextHolder.removeContext();
            }
        };
    }

    private Map<String, String> wrapHeaderMap() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> map = new HashMap<>();
        while (headerNames.nextElement() != null && headerNames.nextElement().length() > 0){
            map.put(headerNames.nextElement(), request.getHeader(headerNames.nextElement()));
        }
       return map;
    }
}
