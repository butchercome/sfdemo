package com.cjw.demo.refactor.book;

/**
 * Created by Javen on 2017/6/9.
 */
public class ReplaceMethodWithMethodObject {
    class order {
        double price(){
            double primaryBasePrice;
            double secondaryBasePrice;
            double tertiaryBasePrice;
            //long computation
            ....
        }
    }
}
