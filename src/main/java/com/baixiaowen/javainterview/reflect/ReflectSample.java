package com.baixiaowen.javainterview.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectSample {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class rc = Class.forName("com.baixiaowen.javainterview.reflect.Robot");
        Robot r = (Robot) rc.newInstance();
        System.err.println("Class name is " + rc.getName());

        Method getHello = rc.getDeclaredMethod("throwHello", String.class);
        getHello.setAccessible(true);
        Object str = getHello.invoke(r, "Bob");
        System.err.println("getHello result is " + str);

        Method sayHi = rc.getMethod("sayHi", String.class);
        sayHi.invoke(r, "welcome");

        Field name = rc.getDeclaredField("name");
        name.setAccessible(true);
        name.set(r, "Alice");
        sayHi.invoke(r, "Welcome");

        System.err.println(System.getProperty("java.ext.dirs"));
        System.err.println(System.getProperty("java.class.path"));

    }
    
}
