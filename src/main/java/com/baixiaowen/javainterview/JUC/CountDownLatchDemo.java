package com.baixiaowen.javainterview.JUC;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        new CountDownLatchDemo().go();
    }

    private void go() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 依次创建3个对象，并完成
        new Thread(() -> {
            System.err.println("线程 ：" + Thread.currentThread().getName() + " 已经到站 " + System.currentTimeMillis());
            countDownLatch.countDown();
            System.err.println("干点别的");
        }, "Thread1").start();
        new Thread(() -> {
            System.err.println("线程 ：" + Thread.currentThread().getName() + " 已经到站 " + System.currentTimeMillis());
            countDownLatch.countDown();
        }, "Thread2").start();
        new Thread(() -> {
            System.err.println("线程 ：" + Thread.currentThread().getName() + " 已经到站 " + System.currentTimeMillis());
            countDownLatch.countDown();
        }, "Thread3").start();
        countDownLatch.await();
        System.err.println("所有线程以到达，主线程开始执行 " + System.currentTimeMillis());
    }

    class Task implements Runnable{

        private CountDownLatch countDownLatch;

        public Task(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.err.println("线程 ：" + Thread.currentThread().getName() + " 已经到站 " + System.currentTimeMillis());
            countDownLatch.countDown();
        }
    }

}