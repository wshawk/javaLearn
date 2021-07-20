package com.example.hawk.java_8.stream;

import java.util.stream.Stream;

/**
 * @author hawk
 * @package com.example.hawk.java_8.stream
 * @desc
 * @date 2021/7/20
 */
public class UseMaxOrMinDemo {
    public static void main(String[] args) {
        // 错误的写法，min() 需要传入一个比较器 Comparator
        System.out.println(getIntegerStream().min(Integer::min).get());

        System.out.println(getIntegerStream().min((x, y) -> x > y ? 1 : -1).get());
        System.out.println(getIntegerStream().max((x, y) -> x > y ? 1 : -1).get());

        System.out.println(getIntegerStream().reduce(Integer::min).get());
    }

    static Stream<Integer> getIntegerStream(){
        return Stream.of(1,3,8,4,5,12,2);
    }
}
