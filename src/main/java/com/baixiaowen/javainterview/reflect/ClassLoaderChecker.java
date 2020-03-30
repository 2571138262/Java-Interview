package com.baixiaowen.javainterview.reflect;

public class ClassLoaderChecker {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader m = new MyClassLoader("C:\\Users\\Administrator\\Desktop\\", "myClassLoader");
        Class c = m.loadClass("Mali");
        System.err.println(c.getClassLoader());
        System.err.println(c.getClassLoader().getParent());
        System.err.println(c.getClassLoader().getParent().getParent());
        System.err.println(c.getClassLoader().getParent().getParent().getParent());
        c.newInstance();
    }
    
}
