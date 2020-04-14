package com.baixiaowen.javainterview.thread;

public class WaitSleepDemo {

    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("thread A is waiting to get lock ");
                synchronized (lock){
                    try {
                        System.err.println("Thread A get lock");
                        Thread.sleep(20);
                        System.err.println("Thread A do wait method");
                        lock.wait();
//                        Thread.sleep(1000);
                        System.err.println("Thread A is done");
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start(); 
        try{
            Thread.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("thread B is waiting to get lock ");
                synchronized (lock){
                    try {
                        System.err.println("Thread B get lock");
                        System.err.println("Thread B is sleeping 10 ms");
                        Thread.sleep(10);
//                        lock.wait(1000);
                        System.err.println("Thread B is Done");
                        lock.notify();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
}
