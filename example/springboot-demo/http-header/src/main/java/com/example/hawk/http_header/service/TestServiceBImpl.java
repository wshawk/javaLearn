package com.example.hawk.http_header.service;

import com.example.hawk.http_header.util.HeaderContextHolder;
import com.example.hawk.http_header.util.HttpRequestUtil;
import com.example.hawk.http_header.util.UserUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * @author hawk
 * @package com.example.hawk.http_header.service
 * @desc
 * @date 2022/6/13
 */
@Service
public class TestServiceBImpl implements TestServiceB{
    @Override
    public void testB() {
        System.out.println(Thread.currentThread().getName() + "===" + HeaderContextHolder.getHeader(HttpHeaders.AUTHORIZATION));

        UserUtils.test();
    }
}
