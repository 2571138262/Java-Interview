package com.baixiaowen.javainterview.JUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只有5个线程同时访问
        final Semaphore semaphore = new Semaphore(5);
        // 模拟20个客户端访问
        for (int i = 0; i < 20; i++) {
            final int NO = i;
            Runnable run = () -> {
                try{
                    // 获取许可
                    semaphore.acquire();
                    System.err.println("Accessing: " + NO);
                    Thread.sleep((long) (Math.random() * 10000));
                    // 访问完成，释放
                    semaphore.release();
                }catch (InterruptedException e){}
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }

}