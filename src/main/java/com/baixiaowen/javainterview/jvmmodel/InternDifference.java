package com.baixiaowen.javainterview.jvmmodel;

public class InternDifference {

    public static void main(String[] args) {
        String s = new String("a");
        s.intern();
        String s2 = "a";
        System.err.println(s == s2);
        
        String s3 = new String("a") + new String("a");
        s3.intern();
        String s4 = "aa";
        System.err.println(s3 == s4);
    }
    
}
