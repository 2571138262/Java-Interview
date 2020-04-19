package com.baixiaowen.javainterview.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 尝试使用ReentrantLock(重入锁) 的公平锁和非公平锁
 */
public class ReentrantLockDemo implements Runnable{

    private static ReentrantLock lock = new ReentrantLock(true); // 公平锁

    public void run() {
        while (true){
            try {
                lock.lock();
                System.err.println(Thread.currentThread().getName() + " get lock");
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo rtld = new ReentrantLockDemo();
        Thread thread1 = new Thread(rtld);
        Thread thread2 = new Thread(rtld);
        thread1.start();
        thread2.start();
    }
}