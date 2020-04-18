package com.baixiaowen.javainterview.thread;

public class CycleWait implements Runnable {

    private String value;
    
    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value = "we have data now";
    }

    public static void main(String[] args) throws InterruptedException {
        CycleWait cw = new CycleWait();
        Thread t = new Thread(cw);
        t.start();
//        while (cw.value == null){
//            Thread.currentThread().sleep(100);
//        }
        t.join();
        System.err.println("value : " + cw.value);
    }
    
}