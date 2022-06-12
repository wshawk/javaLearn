package com.example.hawk.logback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;


/**
 * @author hawk
 * @package com.example.hawk.logback
 * @desc
 * @date 2022/6/12
 */
@SpringBootApplication
public class SpringBootLogBackApplication {
    public static void main(String[] args) {
        // 将System.out/System.err等输出到日志文件中
        SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
        SpringApplication.run(SpringBootLogBackApplication.class);
    }
}
