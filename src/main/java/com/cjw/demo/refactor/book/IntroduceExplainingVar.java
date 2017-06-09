package com.cjw.demo.refactor.book;

/**
 * Created by Javen on 2017/6/9.
 */
public class IntroduceExplainingVar {
    void macContainsIeInit() {
        if ((platform.toUpperCase().indexof("MAC") > -1) &&
                (brower.toUpperCase().indexOf("IE") > -1) &&
                wasInitialized() && resize > 0) {
//        do something
        }
    }

    class IntroduceExplainingVarRefactor {
        void macContainsIeInit() {
            final boolean isMacOs = platform.toUpperCase().indexof("MAC") > -1;
            final boolean isIeBrowser = brower.toUpperCase().indexOf("IE") > -1;
            final boolean wasreSized = resize > 0;
            if (isMacOs && isIeBrowser && wasInitialized() && wasreSized) {
//                do something
            }
        }
    }

}
