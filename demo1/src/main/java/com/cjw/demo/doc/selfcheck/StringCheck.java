package com.cjw.demo.doc.selfcheck;

/**
 * Created by 828471 on 2017/7/10.
 */
public class StringCheck {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "world";
        System.out.println(s1 + "______" + s2);
        change(s1, s2);
        System.out.println(s1 + "______" + s2);
        StringBuffer b1 = new StringBuffer("hello");
        StringBuffer b2 = new StringBuffer("world");
        System.out.println(b1 + "______" + b2);
        change(b1, b2);
        System.out.println(b1 + "______" + b2);
    }

    private static void change(StringBuffer b1, StringBuffer b2) {
        b1 = b2;
        b2.append(b1);
    }

    private static void change(String s1, String s2) {
        s1 = s2;
        s2 = s1 + s2;                         //this  is  a  object  new  create
        System.out.println(s1 + "______" + s2);
    }


}
