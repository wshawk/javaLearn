package com.example.hawk.java_8.function;

import java.util.function.Predicate;

/**
 * @author hawk
 * @package com.example.hawk.java_8.function
 * @desc
 * @date 2021/7/20
 */
public class PredicateDemo {
    public static void main(String[] args) {
        // Predicate<输入类型参数>
        Predicate<Object> p1 = x -> "h".equals(x);
        Predicate<Object> p2 = x -> x.toString() == "2";
        System.out.println(p1.or(p2).negate().test("hh"));
    }
}
