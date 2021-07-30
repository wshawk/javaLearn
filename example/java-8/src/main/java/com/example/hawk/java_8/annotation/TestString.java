package com.example.hawk.java_8.annotation;

/**
 * @author hawk
 * @package com.example.hawk.java_8.annotation
 * @desc
 * @date 2021/7/29
 */
public class TestString {
    public static void main(String[] args) {
        String a = "hello, %s, wish";

        System.out.println(String.format(a, "world"));
    }
}
