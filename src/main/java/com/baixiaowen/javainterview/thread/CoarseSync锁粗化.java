package com.baixiaowen.javainterview.thread;

public class CoarseSync锁粗化 {

    public static String copyString100Times(String target){
        int i = 0;
        // TODO JVM会检测到这样一连串操作都对同一个对象加上同步锁的情况，那么JVM此时就会将加锁的同步范围粗化到整个循环的外部
        StringBuffer sb = new StringBuffer();
        while (i < 100){
            sb.append(target);
        }
        return sb.toString();
    }

}