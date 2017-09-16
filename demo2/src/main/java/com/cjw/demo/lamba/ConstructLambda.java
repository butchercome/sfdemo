package com.cjw.demo.lamba;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by 828471 on 2017/8/15.
 */
public class ConstructLambda {


    @Test
    public void test(){
        java.util.List<String> labels = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        Stream<Button> buttonStream = labels.stream().map(Button::new);
        Button[] buttons1 = buttonStream.toArray(Button[]::new);
        System.out.println(buttonStream);
        System.out.println(buttons1);
    }

}
