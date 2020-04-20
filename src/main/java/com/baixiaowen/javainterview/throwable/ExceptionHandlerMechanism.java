package com.baixiaowen.javainterview.throwable;

public class ExceptionHandlerMechanism {
    
    public static int doWork(){
        try{
            int i = 10 / 0; // 会抛出异常
            System.err.println("i = " + i);
        }catch (ArithmeticException e){ // 捕获异常
            // 捕获 ArithmeticException
            System.err.println("ArithmeticException : " + e);
            return 0;
        }catch (Exception e){
            // 捕获Exception
            System.err.println("Exception: " + e);
            return 1;
        } finally {
            System.err.println("Finally");
            return 2;
        }
    }

    public static void main(String[] args) {
        System.err.println("执行后的值为 ： " + doWork());
        System.err.println("Mission Complete");
    }
    
}
