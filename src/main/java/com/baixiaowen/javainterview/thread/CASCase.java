package com.baixiaowen.javainterview.thread;

/**
 * CAS 原理解析
 */
public class CASCase {

    public volatile int value;

    public void add(){
        value ++;
    }



}