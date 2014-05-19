package com.cliffyuan.tools.jdkapi;

import java.text.NumberFormat;

/**
 * Created by yuanyuanming on 14-5-19.
 */
public class NumberFormatTest {
    public static void main(String[] args) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(20);
        nf.setMaximumFractionDigits(0);
        nf.setGroupingUsed(false);
        System.out.println(nf.format(30));
    }
}
