package org.vtb.lesson15.util;

import org.vtb.lesson15.exception.DirectoryNotFound;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWorker {


    //Напишите метод, который подсчитывает сколько раз в текстовом файле встречается указанная
    //последовательность символов с учетом регистра (только для латиницы). Реализуйте решение на основе
    //FileChannel и ByteBuf;
    public int countSequence(String path, String strSearch) {
        int count = 0, readBytes = 0;
        byte[] byteSearch = strSearch.getBytes();
        byte symbol;
        try (RandomAccessFile aFile = new RandomAccessFile(path, "r");
             FileChannel inChannel = aFile.getChannel()) {
            ByteBuffer buf = ByteBuffer.allocate(8192);
            while (inChannel.read(buf) != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    symbol = buf.get();
                    if (byteSearch[readBytes] == symbol)
                        readBytes++;
                    else {
                        buf.position(buf.position() - readBytes);
                        readBytes = 0;
                    }
                    if (readBytes == byteSearch.length) {
                        count++;
                        readBytes = 0;
                    }
                }
                buf.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    //Напишите метод, который последовательно сшивает все текстовые файлы в указанном каталоге в один
    //файл (подкаталоги обходить не надо) с помощью Java NIO;
    public void joinFilesInCatalog(String path, String pathOut) throws DirectoryNotFound {
        Path inPath = Paths.get(path);
        Path outFile = Paths.get(pathOut);
        if (!Files.isDirectory(inPath))
            throw new DirectoryNotFound();
        try(OutputStream out = Files.newOutputStream(outFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            Stream<Path> files = Files.list(inPath);
            List<Path> fileList = files
                    .filter(p->!Files.isDirectory(p))
                    .filter(p->!p.equals(outFile))
                    .collect(Collectors.toList());
            for(Path file : fileList)
                Files.copy(file,out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Напишите метод, выполняющий поиск файлов размером менее 100 КБ в указанном каталоге
    // и его подкаталогах;
    public void findFilesInCatalogByLessSize(String path, long size) throws DirectoryNotFound {
        Path rootPath = Paths.get(path);
        if (!Files.isDirectory(rootPath))
            throw new DirectoryNotFound();
        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(Files.size(file) < size)
                        System.out.println(file.getFileName());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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
