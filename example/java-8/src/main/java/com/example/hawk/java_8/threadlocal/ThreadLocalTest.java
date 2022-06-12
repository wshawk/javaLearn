package com.example.hawk.java_8.threadlocal;

/**
 * @author hawk
 * @package com.example.hawk.java_8.threadlocal
 * @desc
 * @date 2022/5/25
 */
public class ThreadLocalTest {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    public void set() {
        longLocal.set(1L);
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest threadLocal = new ThreadLocalTest();

        threadLocal.set();     // 初始化ThreadLocal
        for (int i = 0; i < 3; i++) {
            System.out.println(threadLocal.getString() + " : " + threadLocal.getLong() + i);
        }

        Thread thread1 = new Thread() {
            public void run() {
                threadLocal.set();
                for (int i = 0; i < 3; i++) {
                    System.out.println(threadLocal.getString() + " : " + threadLocal.getLong() + i);
                }
            }

            ;
        };
        thread1.start();

        Thread thread2 = new Thread() {
            public void run() {
                threadLocal.set();
                for (int i = 0; i < 3; i++) {
                    System.out.println(threadLocal.getString() + " : " + threadLocal.getLong() + i);
                }
            }

            ;
        };
        thread2.start();
    }
}
