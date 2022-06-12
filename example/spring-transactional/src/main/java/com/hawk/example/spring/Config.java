package com.hawk.example.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hawk
 * @package com.hawk.example.spring
 * @desc
 * @date 2022/3/6
 */
@Configuration
public class Config {
    @Bean
    public Service myService() {
        return new Service();
    }
}
