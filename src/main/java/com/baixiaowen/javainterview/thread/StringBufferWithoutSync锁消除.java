package com.baixiaowen.javainterview.thread;

public class StringBufferWithoutSync锁消除 {

    public void add(String str1, String str2){
        // TODO StringBuffer是线程安全，由于sb只会在append方法中使用，不可能被其他线程引用
        // TODO 因此sb属于不可能共享的资源，JVM会自动消除内部的锁， 就是JVM会自动消除append方法上的synchronize关键字
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str1);
    }

    public static void main(String[] args) {
        StringBufferWithoutSync锁消除 withoutSync = new StringBufferWithoutSync锁消除();
        for (int i = 0; i < 1000; i++) {
            withoutSync.add("111", "222");
        }
    }

}