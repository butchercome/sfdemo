package com.cjw.demo.lamba;

import org.junit.Test;

/**
 * Created by 828471 on 2017/8/15.
 */
public class FunctionLambda {
    public static void testOnePar(MyFunctionalInterface myFunctionalInterface) {
        myFunctionalInterface.single("msg");
    }

    @Test
    public void testOneParameter() {
        testOnePar(x -> System.out.println(x));
    }

    @Test
    public void testOneParameter3() {
        testOnePar(System.out::println);
    }

    @Test
    public void testOneParameter2() {
        testOnePar(new MyFunctionalInterface() {
            @Override
            public void single(String msg) {
                System.out.println(msg);
            }
        });
    }


}

interface MyFunctionalInterface {
    public abstract void single(String msg);
}
