package com.baixiaowen.javainterview.thread;

public class SyncBlockAndMethod {
    
    public void syncsTask(){
        // 同步代码块
        synchronized (this){
            System.err.println("hello");
        }
    }
    
    public synchronized void syncTask(){
        System.err.println("Hello Again");
    }
    
}
