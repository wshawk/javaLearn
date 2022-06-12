package com.example.hawk.http_header.service;

import com.example.hawk.http_header.util.HttpRequestUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * @author hawk
 * @package com.example.hawk.http_header.service
 * @desc
 * @date 2022/6/12
 */
@Service
public class TestServiceImpl implements TestService{
    @Override
    public void test() {
        System.out.println(Thread.currentThread().getName() + "===" + HttpRequestUtil.getHttpHeader(HttpHeaders.AUTHORIZATION).orElse("null"));
    }
}
