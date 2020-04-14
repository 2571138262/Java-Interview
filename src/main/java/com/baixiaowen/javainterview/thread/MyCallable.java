package com.baixiaowen.javainterview.thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        String value = "test";
        System.err.println("Ready to work");
        Thread.currentThread().sleep(5000);
        System.err.println("task done");
        return value;
    }
}
