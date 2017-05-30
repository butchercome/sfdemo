package com.cjw.demo.refactor;

/**
 * Created by 828471 on 2017/5/30.
 */
public class InnerBranchRefactor {
    class BadExample {

        public void someMethod(Object A,Object B){
            if (A != null) {
                if (B != null) {
                    //code
                }
            }
        }

    }

/* ---------------------分割线---------------------- */

    class GoodExample {

        public void someMethod(Object A,Object B){
            if (A != null && B != null) {
                //code
            }
        }

    }
}
