package com.baixiaowen.javainterview.javabasic;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ByteCodeSample {

    public static void main(String[] args) {
        
        int i = 1, j = 5;
        i++;
        ++j;
        System.out.println(i);
        System.out.println(j);
    }


    public void test(String[] arr) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])){
                break;
            }
            set.add(arr[i]);
        }
    }

}
