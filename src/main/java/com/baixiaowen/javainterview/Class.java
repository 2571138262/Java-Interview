package com.baixiaowen.javainterview;

public enum Class {

    CLASS1("一年级"),
    CLASS2("二年级"),
    CLASS3("三年级"),
    CLASS4("四年级"),
    CLASS5("五年级"),
    CLASS6("六年级");

    private String value;

    Class(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static void main(String[] args) {
        System.err.println(Class.CLASS1.getValue());

    }


}
