package com.cjw.demo.lamba.demo;

import org.junit.Test;

import java.util.ArrayList;

import java.util.List;

/**
 * Created by 828471 on 2017/8/15.
 */
public class LambdaTest1 {
    @Test
    public void test1_() {
        java.util.List<Integer> list = this.asList(ArrayList::new, 1, 2, 3, 4, 5);
        list.forEach(System.out::println);
        list.forEach((x) -> System.out.println(x));
    }

    public <T> List<T> asList(MyCrator<List<T>> creator, T... a) {
        List<T> list = creator.create();
        for (T t : a)
            list.add(t);
        return list;
    }
}

interface MyCrator<T extends List<?>> {
    T create();
}

