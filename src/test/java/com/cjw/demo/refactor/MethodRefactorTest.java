package com.cjw.demo.refactor;

import org.junit.Test;

/**
 * Created by 828471 on 2017/5/30.
 */
public class MethodRefactorTest {
    MethodRefactor methodRefactor = new MethodRefactor();

    @Test
    public void methodRefactorTest() {
        MethodRefactor.BadExample bad=new MethodRefactor().new BadExample();
        bad.someMethod1();
    }
}
