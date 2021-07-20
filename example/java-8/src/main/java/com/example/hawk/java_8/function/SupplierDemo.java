package com.example.hawk.java_8.function;

import java.util.function.Supplier;

/**
 * @author hawk
 * @package com.example.hawk.java_8.function
 * @desc
 * @date 2021/7/20
 */
public class SupplierDemo {
    public static void main(String[] args) {
        // Supplier<返回参数类型> --- 生产者
        Supplier<String> s1 = () -> "hello suplier";
        System.out.println(s1.get());
    }
}
