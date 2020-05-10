package com.baixiaowen.javainterview;

public class Test2 {

    private static void change(StringBuffer str11, StringBuffer str12) { // good bad

        str12 = str11; // str2 = good
        str11 = new StringBuffer("new world"); // str1 = new world
        str12.append("new world"); // good new world
    }

    public static void main(String[] args) {
        StringBuffer str1 = new StringBuffer("good ");
        StringBuffer str2 = new StringBuffer("bad ");
        change(str1, str2);
        System.out.println(str1.toString());
        System.out.println(str2.toString());
        new Test2().retrieveObjectById(1L);
    }

    public void retrieveObjectById(Long id){
        try{
            //…抛出 IOException 的代码调用
            //…抛出 SQLException 的代码调用
        }catch(Exception e){
            throw new RuntimeException("Exception in retieveObjectById", e);
        }
    }

}