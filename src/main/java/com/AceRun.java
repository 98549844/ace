package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AceRun {
    private static Logger log = LogManager.getLogger(AceRun.class.getName());


    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();


    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final AceRun test = new AceRun();

        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());
        for (int i = 0; i < 3; i++) {
            Thread thread1 = new Thread() {
                public void run() {
                    test.set();
                    System.out.println(test.getLong());
                    System.out.println(test.getString());
                }
            };
            thread1.start();
            thread1.join();
        }


        System.out.println(test.getLong());
        System.out.println(test.getString());
    }


}
