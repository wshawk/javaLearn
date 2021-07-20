package com.example.hawk.java_8.stream;

import java.util.stream.Stream;

/**
 * @author hawk
 * @package com.example.hawk.java_8.stream
 * @desc
 * @date 2021/7/20
 */
public class UseFlatMapDemo {
    public static void main(String[] args) {
        getStringStream().flatMap(x -> Stream.of(x.split(";"))).forEach(System.out::println);
    }
    static Stream<String> getStringStream(){
        return Stream.of("a;b;c;d","1;2;3;4");
    }
}
