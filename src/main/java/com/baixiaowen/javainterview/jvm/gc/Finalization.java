package com.baixiaowen.javainterview.jvm.gc;

import java.lang.ref.ReferenceQueue;

public class Finalization {
    public static Finalization finalization;

    @Override
    protected void finalize() throws Throwable {
        System.err.println("Finalized");
        finalization = this;
    }

    public static void main(String[] args) {
        Finalization f = new Finalization();
        System.err.println("First print: " + f);
        f = null;
        System.gc();
        try { // 休息一段时间，让上面的垃圾回收线程完成
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.err.println("Second print: " + f);
        System.err.println(f.finalization);

        
    }
    
}
