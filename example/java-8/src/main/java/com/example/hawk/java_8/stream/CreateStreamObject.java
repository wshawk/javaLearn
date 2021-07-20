package com.example.hawk.java_8.stream;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author hawk
 * @package com.example.hawk.java_8.stream
 * @desc
 * @date 2021/7/20
 */
public class CreateStreamObject {
    public static void main(String[] args) {
        // 1. 构建一个空的Stream
        Stream stream01 = Stream.empty();

        // 2. List接口的实现类来创建Stream
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        Stream stream02 = list.stream();

        // 3. Stream.of()
        Stream stream03 = Stream.of("x");
        Stream stream04 = Stream.of("a", "b", "c");

        // 4. Stream.iterator()
        // Stream.iterate()

        // 5. Stream.generate()
        Stream stream05 = Stream.generate(() -> "x").limit(10);
        stream05.forEach(System.out::println);
    }
}
