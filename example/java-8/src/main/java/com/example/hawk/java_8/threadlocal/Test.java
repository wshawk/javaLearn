package com.example.hawk.java_8.threadlocal;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author hawk
 * @package com.example.hawk.java_8.threadlocal
 * @desc
 * @date 2022/5/25
 */
public class Test {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("main");

        System.out.println("currentThreadName: " + Thread.currentThread().getName());
        System.out.println("ThreadLocalValue is: " + threadLocal.get());
        System.out.println("-----------------------------------------------------------");

        Executor executor = Executors.newFixedThreadPool(5);
        for (int i=0; i<10; i++){
            executor.execute(() ->{
               synchronized (Test.class){
                   System.out.println("currentThreadName: " + Thread.currentThread().getName());
                   System.out.println("ThreadLocalValue is: " + threadLocal.get());
                   System.out.println("-----------------------------------------------------------");
               }
            });
        }
    }
}
