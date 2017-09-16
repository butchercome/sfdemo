package com.cjw.demo.lamba;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by 828471 on 2017/8/15.
 */
public class TradCompare {
    @Test
    public void test1() {
        java.util.List<Student> studentList = new ArrayList<Student>() {
            {
                add(new Student("stu1", 100.0));
                add(new Student("stu2", 97.0));
                add(new Student("stu3", 96.0));
                add(new Student("stu4", 95.0));
            }
        };
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getScore(), o2.getScore());
            }
        });
        System.out.println(studentList);
    }

    @Test
    public void test2(){
        ArrayList<Student> studentList = new ArrayList<Student>(){
            {
                add(new Student("stu1",100.0));
                add(new Student("stu2",97.0));
                add(new Student("stu3",96.0));
                add(new Student("stu4",95.0));
            }
        };
        Collections.sort(studentList,(s1,s2)-> Double.compare(s1.getScore(),s2.getScore()));
        System.out.println(studentList);
    }



}
