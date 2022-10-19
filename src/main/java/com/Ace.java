package com;

import com.util.SleepUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Classname: Ace
 * @Date: 2022/10/18 下午 04:15
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int a = 1;
            while (a == 2) {
                System.out.println("t1 start");
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println("Thread.State.RUNNABLE:  " + Thread.State.RUNNABLE);
            if (!t1.getState().equals(Thread.State.RUNNABLE)) {
                System.out.println("跳过");
            } else {
                System.out.println("t2 start");
            }
        });
        t2.start();

/*        Ace ace = new Ace();
        System.out.println(ace.isRunning(ace));

        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);
            if (!ace.isRunning(ace)) {
                System.out.println("skip");
            } else {
                ace.test();
                System.out.println("running");
                SleepUtil.sleep(2);
            }
        }*/

    }

    public void test() {
        int a = 1;
        while (a == 1) {
            System.out.println("t1 start");
        }
    }

    public boolean isRunning(Object object) {
        Thread t = new Thread((Runnable) object);
        AtomicBoolean isRunning = new AtomicBoolean(false);
        Thread thread = new Thread(() -> {
            if (!t.getState().equals(Thread.State.RUNNABLE)) {
                System.out.println(t.getState());
                System.out.println("跳过");
                isRunning.set(true);
            } else {
                System.out.println(t.getState());
                System.out.println("t2 start");
                isRunning.set(false);
            }
        });
        thread.start();
        return isRunning.get();
    }
}

