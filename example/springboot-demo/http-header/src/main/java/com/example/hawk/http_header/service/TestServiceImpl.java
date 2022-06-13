package com.example.hawk.http_header.service;

import com.example.hawk.http_header.util.ContextCopyingDecorator;
import com.example.hawk.http_header.util.HeaderContextHolder;
import com.example.hawk.http_header.util.HttpRequestUtil;
import com.example.hawk.http_header.util.InheritableThreadPoolTaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private TestServiceB testServiceB;

    @Autowired
    private InheritableThreadPoolTaskExecutor inheritableThreadPoolTaskExecutor;

    @Override
    public void test() {
        System.out.println(Thread.currentThread().getName() + "===" + HttpRequestUtil.getHttpHeader(HttpHeaders.AUTHORIZATION).get());
        // 设置请求头
        // HttpRequestUtil.setInheritableServletRequestAttributes();

        extracted();
    }

    private void extracted() {
        inheritableThreadPoolTaskExecutor.setTaskDecorator(new ContextCopyingDecorator());
        inheritableThreadPoolTaskExecutor.execute(this::downloadAndCompressPicture);
    }

    private void downloadAndCompressPicture() {

        System.out.println(Thread.currentThread().getName() + "===" + HeaderContextHolder.getHeader(HttpHeaders.AUTHORIZATION));
        testServiceB.testB();
    }
}
