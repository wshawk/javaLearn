package byJavaConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Run {

    public static void main(String[] args) {
        // 通过IOC容器获得Bean
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringBeanConfig.class);
        HelloWorld helloWorld = (HelloWorld)context.getBean("helloworld");
        helloWorld.getMessage();
    }
}
