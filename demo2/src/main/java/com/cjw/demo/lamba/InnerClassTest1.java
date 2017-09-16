package com.cjw.demo.lamba;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 828471 on 2017/8/17.
 */
public class InnerClassTest1 {

    List<Student> findInvoices2(List<Student> invoices, InvoicePredicate p) {
        List<Student> result = invoices.stream().filter(inv -> p.test(inv)).collect(Collectors.toList());
        return result;
    }

    List<Student> findInvoices3(List<Student> invoices, InvoicePredicate p) {
        List result = new ArrayList<>();
        for (Student inv : invoices) {
            if (p.test(inv)) {
                result.add(inv);
            }
        }
        return result;
    }

    @Test
    List test_findInvoicesGreaterThanAmount(List<Student> invoices, double amount) {
        List result = new ArrayList<>();
        for (Student inv : invoices) {
            if (inv.getScore() > amount) {
                result.add(inv);
            }
        }
        return result;
    }

    @Test
    List test_findInvoicesGreaterThanAmount2(List<Student> invoices, double amount) {
        List result = invoices.stream().filter(inv -> inv.getScore() > amount).collect(Collectors.toList());
        return result;
    }


    @Test
    List<Student> test_findInvoices2(List<Student> invoices, InvoicePredicate p) {
        List<Student> expensiveInvoicesFromOracle = findInvoices2(invoices, new InvoicePredicate() {
            @Override
            public boolean test(Student inv) {
                return false;
            }
        });
        return expensiveInvoicesFromOracle;
    }


}

interface InvoicePredicate {

    boolean test(Student inv);

}
