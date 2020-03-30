package com.baixiaowen.javainterview.reflect;

public class LoadDifference {

    public static void main(String[] args) throws ClassNotFoundException {
        // 获取ClassLoader
        ClassLoader cl = Robot.class.getClassLoader();
        cl.loadClass("com.baixiaowen.javainterview.reflect.Robot");
        // 通过forName来加载Robot
        Class c = Class.forName("com.baixiaowen.javainterview.reflect.Robot");
        
        Class.forName("com.mysql.jdbc.Driver");
    }
    
}
