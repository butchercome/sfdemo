package com.cjw.demo.service.log;

/**
 * Created by 828471 on 2017/5/29.
 */
public class LogOutPut {
    public void getCallerClassMethod(){
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        String className=stacks[1].getClassName();
        String methodname=stacks[1].getMethodName();
        System.out.println("this class name is "+className+" this method name is "+methodname);



    }
    
    public static void main(String[] args) {
//        getCallerClass();
    }

}
