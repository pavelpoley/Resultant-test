package com.pavelpoley.resultant_test.common;

import java.text.DecimalFormat;


/**
 * Helper class to format values
 * */
public class FormatUtils {

    private static DecimalFormat df = new DecimalFormat("0.00");

    public static String formatVolume(int num) {
        return String.valueOf(num);
    }

    public static String formatAmount(double num) {
        return df.format(num);
    }

}
