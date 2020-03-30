package com.baixiaowen.javainterview.jvmmodel;

public class Fibonacci {
    
    public static int finonacci(int n){
        if (n == 0) return 0;
        if (n == 1) return 1;
        return finonacci(n - 1) + finonacci(n - 2);
    }

    public static void main(String[] args) {
        System.err.println(finonacci(100000));
    }
    
}
