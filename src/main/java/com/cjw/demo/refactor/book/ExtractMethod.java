package com.cjw.demo.refactor.book;

/**
 * Created by Javen on 2017/6/6.
 */
public class ExtractMethod {
    void printOwing(double amount){
        printBanner();

        //print detail
        System.out.println("name:" + _name);
        System.out.println("amount" + amount);
    }

    class ExtractMethodRefactor{
        void printOwing(double amount){
            printBanner();
            printDetails(amount);

        }
        void printDetails(double amount){
            System.out.println("name:" + _name);
            System.out.println("amount" + amount);
        }
    }


}
