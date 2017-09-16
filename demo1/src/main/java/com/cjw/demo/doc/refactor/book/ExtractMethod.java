package com.cjw.demo.doc.refactor.book;

/**
 * Created by Javen on 2017/6/6.
 */
public class ExtractMethod {
    private String  _name="1";
    void printOwing(double amount){
        printBanner();

        //print detail
        System.out.println("name:" + _name);
        System.out.println("amount" + amount);
    }

    private void printBanner() {

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
