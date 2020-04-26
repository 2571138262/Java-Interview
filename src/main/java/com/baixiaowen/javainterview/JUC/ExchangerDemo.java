package com.baixiaowen.javainterview.JUC;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        // 代表男生和女生
        ExecutorService exec = Executors.newFixedThreadPool(2);

        exec.execute(() -> {
            try {
                // 女生对男生说的话
                String girl = exchanger.exchange("我其实暗恋你很久了 ... ");
                System.err.println("女生说： " + girl);
            }catch (InterruptedException e){}
        });

        exec.execute(() -> {
            try {
                System.err.println("女生慢慢的从教室里走出来 ...");
                TimeUnit.SECONDS.sleep(3);
                // 男生对女生说的话
                String boy = exchanger.exchange("我很喜欢你 ... ");
                System.err.println("男生说： " + boy);
            }catch (InterruptedException e){}
        });
    }

}