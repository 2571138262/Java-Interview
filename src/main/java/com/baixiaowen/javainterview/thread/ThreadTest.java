package com.baixiaowen.javainterview.thread;

public class ThreadTest {
    
    private static void attack(){
        System.err.println("Fight");
        System.err.println("Current Thread is : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
                attack();
            }
        };
        System.err.println("Current main thread is : " + Thread.currentThread().getName());
//        t.run();
        t.start();
        t.join();
        t.start();
    }
    
}
