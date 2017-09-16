package com.cjw.demo.lamba;

import org.junit.Test;

/**
 * Created by 828471 on 2017/8/15.
 */
public class ThreadLambda {
    @Test
    public void testThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello, i am thread!");
            }
        }).start();
    }

    @Test
    public void testThread_(){
        new Thread(()-> System.out.println("hello, i am thread!")).start();
    }
}
