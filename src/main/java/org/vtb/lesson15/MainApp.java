package org.vtb.lesson15;


import org.vtb.lesson15.exception.DirectoryNotFound;
import org.vtb.lesson15.util.FileWorker;

import java.util.List;

public class MainApp {


    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker();
        // 1
        /*System.out.println(fileWorker
                .countSequence("src\\main\\resources\\task1\\demo.txt", "abcd")); //abc

         */
        // 2
        try {
            fileWorker.joinFilesInCatalog("src\\main\\resources\\task2", "src\\main\\resources\\task2\\out.txt");
        } catch (DirectoryNotFound directoryNotFound) {
            directoryNotFound.printStackTrace();
        }

        // 3
        // создание
//        fileWorker.createCatalog(List.of("src\\main\\resources\\task3\\task3.1\\task3.1.1",
//                "src\\main\\resources\\task3\\task3.1\\task3.1.2",
//                "src\\main\\resources\\task3\\task3.2"));
//        fileWorker.createFiles(List.of(
//                "src\\main\\resources\\task3\\1.txt", "src\\main\\resources\\task3\\2.txt",
//                "src\\main\\resources\\task3\\task3.1\\3.txt",
//                "src\\main\\resources\\task3\\task3.1\\task3.1.1\\4.txt"));
        // поиск
        /*try {
            fileWorker.findFilesInCatalogByLessSize("src\\main\\resources\\task3", 102400);
        } catch (DirectoryNotFound e) {
            e.printStackTrace();
        }*/

    }

}
