package org.vtb.lesson4.exception;

public class MyArraySizeException extends Exception {

    public MyArraySizeException() {
        super("Матрица должна быть размера 4x4");
    }

    public MyArraySizeException(String message, int length, int weight) {
        super(message + " " + length + " " + weight);
    }
}
