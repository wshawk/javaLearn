package com.example.hawk.java_8.stream;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * @author hawk
 * @package com.example.hawk.java_8.stream
 * @desc
 * @date 2021/7/20
 */
public class UseReduceDemo {
    public static void main(String[] args) {
        // filter
        getStringStream().filter(x -> x.contains("a")).forEach(System.out::println);

        // reduce 不增加初始值，第一个x值为 stream的第一个值，返回的是optional对象
        // Optional<T> reduce(BinaryOperator<T> accumulator);
        System.out.println(getStringStream().reduce((x, y) -> {
            x = x + "_" + y;
            return x;
        }));

        // 增加一个初始值，第一个x值为初始值,并且返回对象不是optional对象
        // T reduce(T identity, BinaryOperator<T> accumulator);
        System.out.println(getStringStream().reduce("", (x, y) -> {
            x = x + "_" + y;
            return x;
        }));

        // <U> U reduce(U identity,BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner);
        // 可以返回任意类型的数据
        System.out.println(getStringStream().reduce("", (x, y) -> {
            return x + y;
        }, (x, y) -> {
            return null;
        }));
    }

    static Stream<String> getStringStream() {
        return Stream.of("a", "ab", "bc");
    }
}
