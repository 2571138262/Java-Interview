package com.baixiaowen.javainterview.throwable;

public class ExceptionPerformance测试TryCatch的性能 {
    
    public static void testException(String[] array){
        try {
            System.err.println(array[0]);
        } catch (NullPointerException e){
            System.err.println("array cannot be null");
        }
    }

    public static void testIf(String[] array){
        if (array != null){
            System.err.println(array[0]);
        } else {
            System.err.println("array cannot be null");
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
//        testException(null);
        testIf(null);
        System.err.println("cost : " + (System.nanoTime() - start));
    }
    
}
