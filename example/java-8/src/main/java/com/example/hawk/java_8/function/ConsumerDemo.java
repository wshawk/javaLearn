package com.example.hawk.java_8.function;

import java.util.function.Consumer;

/**
 * @author hawk
 * @package com.example.hawk.java_8
 * @desc
 * @date 2021/7/20
 */
public class ConsumerDemo {
    /**
     * Consumer 接收一个参数，无返回值
     * @param args
     */
    public static void main(String[] args) {
        Consumer consumer01 = new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println("最初的consumer");
            }
        };

        // consumer 消费
        consumer01.accept(null);

        Consumer consumer02 = (x) -> System.out.println(x + " hello consumer");
        System.out.println("------------------------------");
        // consumer02 执行结束后 再执行 consumer01
        consumer02.andThen(consumer01).accept("hawk");
    }
}
