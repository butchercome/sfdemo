package com.cjw.demo.lamba;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by 828471 on 2017/8/15.
 */
public class MethodLambda {
    @Test
    public void test1_() {
        java.util.List<String> strLst = new ArrayList<String>() {
            {
                add("adfkjsdkfjdskjfkds");
                add("asdfasdfafgfgf");
                add("public static void main");
            }
        };
        Collections.sort(strLst, String::compareToIgnoreCase);
        System.out.println(strLst);
    }

    @Test
    public void test1_2() {
        java.util.List<String> strLst = new ArrayList<String>() {
            {
                add("adfkjsdkfjdskjfkds");
                add("asdfasdfafgfgf");
                add("public static void main");
            }
        };
        Collections.sort(strLst, new Comparator<String>() {
            @Override
            public int compare(String s, String str) {
                return s.compareToIgnoreCase(str);
            }
        });
        System.out.println(strLst);
    }

    @Test
    public void test1_3() {
        java.util.List<String> strLst = new ArrayList<String>() {
            {
                add("adfkjsdkfjdskjfkds");
                add("asdfasdfafgfgf");
                add("public static void main");
            }
        };
        Collections.sort(strLst, (s, str) -> s.compareToIgnoreCase(str));
        System.out.println(strLst);
    }
}
