package com.example.hawk.http_header.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author hawk
 * @package com.example.hawk.http_header.controller
 * @desc
 * @date 2022/6/12
 */
public class HttpRequestUtil {
    public static Optional<HttpServletRequest> getHttpServletRequest(){
        return getHttpServletRequestAttributes().map(ServletRequestAttributes::getRequest);
    }

    public static  Optional<ServletRequestAttributes> getHttpServletRequestAttributes() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(s ->(ServletRequestAttributes) s);
    }

    public static Optional<ServletRequestAttributes> setInheritableServletRequestAttributes(){
        Optional<ServletRequestAttributes> httpServletRequestAttributes = getHttpServletRequestAttributes();
        httpServletRequestAttributes.ifPresent(servletRequestAttributes -> RequestContextHolder.setRequestAttributes(servletRequestAttributes, true));
        return httpServletRequestAttributes;
    }

    public static Optional<String> getHttpHeader(String name){
        return getHttpServletRequest().map(req -> req.getHeader(name));
    }
}
