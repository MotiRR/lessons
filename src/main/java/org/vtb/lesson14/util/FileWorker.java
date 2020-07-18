package org.vtb.lesson14.util;

import org.vtb.lesson14.exception.DirectoryNotFound;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class FileWorker {


    //Напишите метод, который подсчитывает сколько раз в текстовом файле встречается указанная
    //последовательность символов с учетом регистра (только для латиницы);
    public int countSequence(String path, String strSearch) {
        int count = 0, readBytes = 0;
        long total = 0;
        String read;
        byte[] arr = new byte[strSearch.length()];
        try (RandomAccessFile raf = new RandomAccessFile(path, "r")) {
            while ((readBytes = raf.read(arr)) > -1) {
                if (readBytes < strSearch.length()) break;
                total += readBytes;
                read = new String(arr, StandardCharsets.UTF_8);
                if (read.equals(strSearch))
                    count++;
                else {
                    total -= strSearch.length() - 1;
                    raf.seek(total);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    //Напишите метод, который последовательно сшивает все текстовые файлы в указанном каталоге в один файл
    //(подкаталоги обходить не надо);
    public void joinFiles(List<String> paths, String pathOut) {

        try (SequenceInputStream seq = new SequenceInputStream(getElements(paths));
             FileOutputStream out = new FileOutputStream(pathOut)) {

            out.write(seq.readAllBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Enumeration getElements(List<String> paths) throws FileNotFoundException {
        Vector<FileInputStream> pathsIn = new Vector();
        for (String path : paths) {
            pathsIn.add(new FileInputStream(path));
        }
        return pathsIn.elements();
    }

    //Напишите метод, позволяющий удалить каталог с вложенными файлами и каталогами;
    public void deleteCatalog(String path) throws DirectoryNotFound {
        File file = new File(path);
        if(!file.isDirectory()) throw new DirectoryNotFound();
        recursiveDelete(file);
    }

    private void recursiveDelete(File file) {
        if (!file.exists())
            return;

        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                recursiveDelete(f);
            }
        }
        file.delete();
        System.out.println("Удаленный файл или папка: " + file.getAbsolutePath());
    }

    public void createCatalog(List<String> paths) {
        for (String path : paths) {
            new File(path).mkdirs();
        }
    }

    public void createFiles(List<String> files) {
        for (String path : files) {
            try {
                new File(path).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
