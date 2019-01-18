package byZHUJIE;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext
                ("spring-ZHUJIE.xml");
        HelloWorld helloWorld = (HelloWorld) ctx.getBean("hello");
        helloWorld.getMessage();
    }
}
