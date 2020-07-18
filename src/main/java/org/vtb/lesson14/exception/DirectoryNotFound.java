package org.vtb.lesson14.exception;

public class DirectoryNotFound extends Exception {

    public DirectoryNotFound() {
        super("Укажите путь до существующего каталога");
    }

    public DirectoryNotFound(String message) {
        super(message);
    }
}
