package com.baixiaowen.javainterview.throwable;

import java.io.FileNotFoundException;

public class ErrorAndException {

    public void throwError(){
        throw new StackOverflowError();
    }

    public void throwRuntimeException(){
        throw new RuntimeException();
    }

    public void throwCheckedException(){
        try {
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ErrorAndException eae = new ErrorAndException();
        eae.throwError();
        eae.throwRuntimeException();
        eae.throwCheckedException();
    }

}