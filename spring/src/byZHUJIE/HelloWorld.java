package byZHUJIE;

import org.springframework.stereotype.Component;

@Component("hello")
public class HelloWorld {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println("Hello Spring By 注解");
    }
}
