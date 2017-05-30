package com.cjw.demo.refactor;

/**
 * Created by 828471 on 2017/5/30.
 */
public class MethodProvideRefactor {
    class BadExample {

        public int someMethod(Data data) {
            int i = data.getI();
            int j = data.getJ();
            int k = data.getK();
            return i * j * k;
        }

        public class Data {

            private int i;
            private int j;
            private int k;

            public Data(int i, int j, int k) {
                super();
                this.i = i;
                this.j = j;
                this.k = k;
            }

            public int getI() {
                return i;
            }

            public int getJ() {
                return j;
            }

            public int getK() {
                return k;
            }

        }

    }

/* ---------------------分割线---------------------- */

    class GoodExample {

        public int someMethod(Data data) {
            return data.getResult();
        }

        public class Data {

            private int i;
            private int j;
            private int k;

            public Data(int i, int j, int k) {
                super();
                this.i = i;
                this.j = j;
                this.k = k;
            }

            public int getI() {
                return i;
            }

            public int getJ() {
                return j;
            }

            public int getK() {
                return k;
            }

            public int getResult() {
                return i * j * k;
            }

        }

    }
}
