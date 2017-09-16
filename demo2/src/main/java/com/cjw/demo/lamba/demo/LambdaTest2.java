package com.cjw.demo.lamba.demo;

import org.junit.Test;

/**
 * Created by 828471 on 2017/8/15.
 */
public class LambdaTest2 {
    public void doWork1() {
        Runnable runnable = () -> {
            System.out.println(this.toString());
            System.out.println("lambda express run...");
        };
        new Thread(runnable).start();
    }

    public void doWork2() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(this.toString());
                System.out.println("anony function run...");
            }
        };
        new Thread(runnable).start();
    }

    @Test
    public void test() {
        new LambdaTest2().doWork1();
        new LambdaTest2().doWork2();
    }
}
