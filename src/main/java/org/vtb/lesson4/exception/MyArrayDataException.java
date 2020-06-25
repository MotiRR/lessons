package org.vtb.lesson4.exception;

public class MyArrayDataException extends RuntimeException {

    public MyArrayDataException() {
       super("Матрица должна содержать числа");
    }

    public MyArrayDataException(String message) {
        super(message);
    }
}
