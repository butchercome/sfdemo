package com.cjw.demo.refactor;

/**
 * Created by 828471 on 2017/5/30.
 */
public class ImplementRefactor {
    class BadExample {

        public void someMethod1(){
            send("您的操作已成功！");
        }

        public void someMethod2(){
            send("您的操作已成功！");
        }

        public void someMethod3(){
            send("您的操作已成功！");
        }

        private void send(String message){
            //code
        }
    }

/* ---------------------分割线---------------------- */

    class GoodExample {

        protected static final String SUCCESS_MESSAGE = "您的操作已成功！";

        public void someMethod1(){
            send(SUCCESS_MESSAGE);
        }

        public void someMethod2(){
            send(SUCCESS_MESSAGE);
        }

        public void someMethod3(){
            send(SUCCESS_MESSAGE);
        }

        private void send(String message){
            //code
        }

    }
}
