package com.cjw.demo.doc.refactor;

/**
 * Created by 828471 on 2017/5/30.
 */
public class MethodRefactor {

    class BadExample {

        public void someMethod1() {
            //code
            System.out.println("重复代码");/* 重复代码块 */
            //code
        }

        public void someMethod2() {
            //code
            System.out.println("重复代码");/* 重复代码块 */
            //code
        }

    }

    class GoodExample {

        public void someMethod1() {
            //code
            someMethod3();
            //code
        }

        public void someMethod2() {
            //code
            someMethod3();
            //code
        }

        public void someMethod3() {
            System.out.println("重复代码");/* 重复代码块 */
        }

    }
}
