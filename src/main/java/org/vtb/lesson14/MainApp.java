package org.vtb.lesson14;


import org.vtb.lesson14.exception.DirectoryNotFound;
import org.vtb.lesson14.util.FileWorker;

import java.util.List;

public class MainApp {


    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker();
        // 1
        System.out.println(fileWorker
                .countSequence("src\\main\\resources\\task1\\demo.txt", "abcd")); //abc
        // 2
        fileWorker.joinFiles(List.of(
                "src\\main\\resources\\task2\\1.txt", "src\\main\\resources\\task2\\2.txt",
                "src\\main\\resources\\task2\\3.txt"), "src\\main\\resources\\task2\\out.txt");

        // 3
        // создание
//        fileWorker.createCatalog(List.of("src\\main\\resources\\task3\\task3.1\\task3.1.1",
//                "src\\main\\resources\\task3\\task3.1\\task3.1.2",
//                "src\\main\\resources\\task3\\task3.2"));
//        fileWorker.createFiles(List.of(
//                "src\\main\\resources\\task3\\1.txt", "src\\main\\resources\\task3\\2.txt",
//                "src\\main\\resources\\task3\\task3.1\\3.txt",
//                "src\\main\\resources\\task3\\task3.1\\task3.1.1\\4.txt"));
        // удаление
        try {
            fileWorker.deleteCatalog("src\\main\\resources\\task3");
        } catch (DirectoryNotFound e) {
            e.printStackTrace();
        }

    }

}
