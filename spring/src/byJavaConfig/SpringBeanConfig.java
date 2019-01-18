package byJavaConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "byJavaConfig")
public class SpringBeanConfig {

    @Bean(name = "helloworld")
    public HelloWorld getHelloWorld() {
        return new HelloWorld();
    }
}
