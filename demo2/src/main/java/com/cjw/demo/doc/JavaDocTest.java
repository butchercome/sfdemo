package com.cjw.demo.doc;

import com.sun.tools.javadoc.Main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by 828471 on 2017/8/9.
 */
public class JavaDocTest {

    public static void main(String[] args) {
        PrintStream originalOutput = System.out;
        try {
            doTest();
        } finally {
            // restore things
            System.setOut(originalOutput);
        }
    }
    static void doTest() {
        ByteArrayOutputStream redirectedOutput = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;

        // redirect System.out to a buffer
        System.setOut(new PrintStream(redirectedOutput));

        PrintWriter sink = new PrintWriter(new ByteArrayOutputStream());

        // execute javadoc
        int result = Main.execute("javadoc", sink, sink, sink,
                "com.sun.tools.doclets.standard.Standard",
                new String[]{"p"}
        );


        // test whether javadoc did any output to System.out
        if (redirectedOutput.toByteArray().length > 0) {
            originalOutput.println("Test failed; here's what javadoc wrote on its standard output:");
            originalOutput.println(redirectedOutput.toString());
            throw new Error("javadoc output wasn\'t properly redirected");
        } else if (result != 0) {
            throw new Error("javadoc run failed");
        } else {
            originalOutput.println("OK, good");
        }
    }

}
