package com.baixiaowen.javainterview.jvm.gc;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ReferenceQueueTest {
    private static ReferenceQueue<NormalObject> rq = new ReferenceQueue();

    private static void checkQueue() {
        Reference<NormalObject> ref = null;
        while ((ref = (Reference<NormalObject>) rq.poll()) != null) {
            if (ref != null) {
                System.err.println("In Queue: " + ((NormalObjectWeakReference) ref).name);
                System.err.println("reference object: " + ref.get());
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<WeakReference<NormalObject>> weakList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            weakList.add(new NormalObjectWeakReference(new NormalObject("weak " + i), rq));
            System.err.println("Create weak: " + weakList.get(i));
        }
        System.err.println("first time");
        checkQueue();
        System.gc();
        try {
            Thread.currentThread().sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.err.println("second time");
        checkQueue();
    }
    
}
