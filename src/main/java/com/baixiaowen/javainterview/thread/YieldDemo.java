package com.baixiaowen.javainterview.thread;

public class YieldDemo {

    public static void main(String[] args) {
        Runnable yieldTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.err.println(Thread.currentThread().getName() + i);
                    if (i == 5){
                        Thread.yield();
                    }
                }
            }
        };
        Thread t1 = new Thread(yieldTask, "A");
        Thread t2 = new Thread(yieldTask, "B");
        t1.start();
        t2.start();
    }
    
}
