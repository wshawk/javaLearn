package com.example.hawk.java_8.function;

import java.util.function.Function;

/**
 * @author hawk
 * @package com.example.hawk.java_8.function
 * @desc
 * @date 2021/7/20
 */
public class FunctionDemo {
    public static void main(String[] args) {
        // Function<入参类型, 返参类型>
        Function<String, Integer> f1 = (x) -> {
            System.out.println("执行了f1");
            return Integer.parseInt(x) + 1; };

        // Integer s = f1.apply("5");
        // System.out.println(s);

        Function<Integer, Integer> f2 = x -> {
            System.out.println("执行了f2");
            return x+1;
        };
        // compose 先获取 f1 执行 apply 之后的结果 ，作为f2 的输入，隐藏了f2 的apply调用
        System.out.println(f2.compose(f1).apply("5"));
        /**
         * identity方法会返回一个不进行任何处理的Function，即输出与输入值相等；
         */
        System.out.println(Function.identity());

        Function<String, String> f3 = x -> x.toLowerCase();
        Function<String, String> f4 = x -> x.toUpperCase();
        // 先执行f3 然后执行 f4
        System.out.println(f3.andThen(f4).apply("x"));
    }
}
