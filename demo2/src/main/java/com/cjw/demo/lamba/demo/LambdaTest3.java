package com.cjw.demo.lamba.demo;

import org.junit.Test;

/**
 * Created by 828471 on 2017/8/15.
 */
public class LambdaTest3 {

    public void test(int x, int y) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int z = 100;
                int total = z + x + y;
                System.out.println("total:" + total);
            }
        }).start();
    }

    public void test2(int x, int y) {
        new Thread(() -> {
            int z = 100;
            int total = z + x + y;
            System.out.println("total:" + total);
        }).start();
    }

    @Test
    public void test(){
        test2(2,2);
    }


}
