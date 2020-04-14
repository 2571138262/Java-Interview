package com.baixiaowen.javainterview;

import java.math.BigDecimal;

public class Test {

    public static void main(String[] args) {

        BigDecimal a = new BigDecimal(3.6666666666);
        BigDecimal b = new BigDecimal(122222);
        System.out.println(a.setScale(4, BigDecimal.ROUND_UP));
        System.out.println(b.setScale(2, BigDecimal.ROUND_UP));

        String dataVal = "3014";
        String dataCoefficient = "0.1";
        String dataDecimals = "2";
        BigDecimal bd1 = new BigDecimal(dataVal);
        BigDecimal bd2 = new BigDecimal(dataCoefficient);
        dataVal = String.valueOf(bd1.multiply(bd2).doubleValue());
        BigDecimal x = new BigDecimal(dataVal);
        x.setScale(Integer.valueOf(dataDecimals), BigDecimal.ROUND_UP);
        dataVal = String.valueOf(x);

        dataVal = dataVal + " " + "V";
        System.err.println(dataVal);
        
        String key = "product_key:a1Xv2RDb11B:device_key:KSG1_100K_DEVICE02";
        String field = "voltageRS";
   
    }

}
