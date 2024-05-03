package vfespw.runner;

import java.util.List;

public final class TestUtils {  // extends BaseLocators  //extends BaseLocators BaseTest
    public static double getMax(List<Double> list) {
        double max = Double.MIN_VALUE;
        for (Double number : list) {
            max = Math.max(max, number);
        }
        return max;
    }

    public static boolean isDescending(List<Double> list) {
        Double previousNumber = list.get(0);
        for (Double number : list) {
            if (number > previousNumber) {

                return false;
            } else {
                previousNumber = number;
            }
        }
        return true;
    }
}
