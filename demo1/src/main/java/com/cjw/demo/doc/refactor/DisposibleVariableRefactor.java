package com.cjw.demo.doc.refactor;

/**
 * Created by 828471 on 2017/5/30.
 */
public class DisposibleVariableRefactor {
    class BadExample {

        public void someMethod(int i,int j,int k,int l,int m,int n){
            //code
        }

    }

/* ---------------------分割线---------------------- */

    class GoodExample {

        public void someMethod(Data data){
            //code
        }

    }

    class Data{

        private int i;
        private int j;
        private int k;
        private int l;
        private int m;
        private int n;


    }
}
