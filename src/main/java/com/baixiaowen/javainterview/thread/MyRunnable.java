package com.baixiaowen.javainterview.thread;

public class MyRunnable implements Runnable {

    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.err.println("Thread start : " + this.name + ", i = " + i);
        }
    }
}
