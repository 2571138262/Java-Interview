package com.baixiaowen.javainterview.jvm.gc;

public class NormalObject {
    
    public String name;

    public NormalObject(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.err.println("Finalizing obj " + name);
    }
}
