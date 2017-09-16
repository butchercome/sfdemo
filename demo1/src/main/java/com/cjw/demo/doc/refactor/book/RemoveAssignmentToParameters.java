package com.cjw.demo.doc.refactor.book;

/**
 * Created by 828471 on 2017/6/9.
 */
public class RemoveAssignmentToParameters {
    int dicount(int inputVal, int quantity, int yearToDate) {
        if (inputVal > 50) inputVal -= 5;
        if (quantity > 100) quantity -= 10;
        if (yearToDate > 1000) yearToDate -= 100;
        return inputVal;
    }

    double potentialEnergy(double mass, double height) {
        return mass * 9.81 * height;
    }

    class RemoveAssignmentToParametersRefactor {
        int dicount(int inputVal, int quantity, int yearToDate) {
            int result = inputVal;
            if (result > 50) result -= 5;
            if (quantity > 100) quantity -= 10;
            if (yearToDate > 1000) yearToDate -= 100;
            return result;
        }

        double potentialEnergy(double mass, double height) {
            return mass * GRAVITATIONAL_CONSTANT * height;
        }

        static final double GRAVITATIONAL_CONSTANT = 9.81;
    }

}
