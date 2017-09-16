package com.cjw.demo.doc.refactor;

/**
 * Created by 828471 on 2017/5/30.
 */
public class BranchRefactor {
    class BadExample {

        public void someMethod(Object A,Object B){
            if (A != null) {
                if (B != null) {
                    //code[1]
                }else {
                    //code[3]
                }
            }else {
                //code[2]
            }
        }

    }

/* ---------------------分割线---------------------- */

    class GoodExample {

        public void someMethod(Object A,Object B){
            if (A == null) {
                //code[2]
                return;
            }
            if (B == null) {
                //code[3]
                return;
            }
            //code[1]
        }

    }
}
