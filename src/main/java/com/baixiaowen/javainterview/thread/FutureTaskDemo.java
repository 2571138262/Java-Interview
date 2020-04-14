package com.baixiaowen.javainterview.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new MyCallable());
        new Thread(task).start();
        if (!task.isDone()){
            System.err.println("task has not finished, please wait!");
        }
        System.err.println("task return : " + task.get());
    }
    
}
