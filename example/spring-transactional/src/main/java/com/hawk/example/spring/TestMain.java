package com.hawk.example.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hawk
 * @package com.hawk.example.spring
 * @desc
 * @date 2022/3/6
 */
public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        Service myService = ctx.getBean(Service.class);
        myService.hello();
    }
}
