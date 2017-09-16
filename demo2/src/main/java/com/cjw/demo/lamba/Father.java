package com.cjw.demo.lamba;

import org.junit.Test;

/**
 * Created by 828471 on 2017/8/15.
 */
public class Father {
    public void greet() {
        System.out.println("Hello, i am function in father!");
    }

    /**
     *this is  a example
     * tell us  lambda when use
     * who is  its owner
     * not the base class but foe the creator
     */
    class Child extends Father {

        @Override
        public void greet() {
            Runnable runnable = super::greet;
            new Thread(runnable).start();
        }

        //        @Override
        public void greet2() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Father().greet();
                }
            }).start();
        }
    }

    @Test
    public void test1() {
        new Child().greet();

    }

    @Test
    public void test2() {
        new Child().greet2();
    }

}

