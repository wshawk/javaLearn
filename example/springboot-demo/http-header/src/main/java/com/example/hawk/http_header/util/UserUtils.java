package com.example.hawk.http_header.util;

import org.springframework.http.HttpHeaders;

/**
 * @author hawk
 * @package com.example.hawk.http_header.util
 * @desc
 * @date 2022/6/13
 */
public class UserUtils {

    public static void test() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        // ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        // HttpServletRequest request = attributes.getRequest();
        // String httpHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        // System.out.println(Thread.currentThread().getName() + "============" + httpHeader);
        System.out.println(Thread.currentThread().getName() + "===" + HeaderContextHolder.getHeader(HttpHeaders.AUTHORIZATION));
    }
}
