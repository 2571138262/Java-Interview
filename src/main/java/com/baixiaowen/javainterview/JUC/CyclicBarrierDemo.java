package com.baixiaowen.javainterview.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        new CyclicBarrierDemo().go();
    }

    public void go(){
        // 初始化栅栏的参与线程数为3
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        // 依次创建3个线程，并启动
        new Thread(() -> {
            System.err.println("线程 " + Thread.currentThread().getName() + " 已经到达 " + System.currentTimeMillis());
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.err.println("线程 " + Thread.currentThread().getName() + " 开始处理 " + System.currentTimeMillis());
        }, "Thread 1 ").start();

        new Thread(() -> {
            System.err.println("线程 " + Thread.currentThread().getName() + " 已经到达 " + System.currentTimeMillis());
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.err.println("线程 " + Thread.currentThread().getName() + " 开始处理 " + System.currentTimeMillis());
        }, "Thread 2 ").start();

        new Thread(() -> {
            System.err.println("线程 " + Thread.currentThread().getName() + " 已经到达 " + System.currentTimeMillis());
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.err.println("线程 " + Thread.currentThread().getName() + " 开始处理 " + System.currentTimeMillis());
        }, "Thread 3 ").start();

    }

}