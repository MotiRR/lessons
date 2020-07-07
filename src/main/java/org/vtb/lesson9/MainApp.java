package org.vtb.lesson9;

import org.vtb.lesson3.Array;
import org.vtb.lesson7.entity.MyEntry;
import org.vtb.lesson9.entity.ForkJoinTask;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class MainApp {

    static final Random random = new Random();
    static int[] arr = createArray();

    /*С помощью RecursiveTask и ForkJoinPool.commonPool() реализуйте поиск максимального элемента в
    целочисленном массиве (int[]). Размер массива: 100.000.000, в каждой ячейке лежит случайное число
    от 0 до 100000. Сравните скорость выполнения с однопоточным вариантом;


    Ту же задачу выполните через stream() и parallelStream();
    Ответьте на вопрос: какой вариант самый быстрый? Замеры времени в мс добавьте в комментариях к коду.
*/
    private static int[] createArray() {
        int bound = 100000;
        int size = 100_000_000;
        int[] arr = new int[size];
        for(int i = 0; i < size; i++)
            arr[i] = random.nextInt(bound);
        return arr;
    }

    //RecursiveTask, когда if (this.data.length > 100_000: 1225
    //RecursiveTask (one thread), когда if (this.data.length > 100_000_000): 51
    //main (one thread): 31
    private static void runTaskOne() {
        int max = 0;
        long start = 0L;
        findMaxOneThread();
        ForkJoinTask demoRecursiveTask = new ForkJoinTask(arr, 100_000);
        start = System.currentTimeMillis();
        max = ForkJoinPool.commonPool().invoke(demoRecursiveTask);
        System.out.format("(ForkJoinTask) Максимальное значение=%d, затраченное время=%d\n",
                max, System.currentTimeMillis() - start);
    }

    private static void findMaxOneThread() {
        int max = 0;
        long start = 0L;
        start = System.currentTimeMillis();
        for (int value : arr)
            if (value > max) max = value;
        System.out.format("(Main) Максимальное значение=%d, затраченное время=%d\n",
                max, System.currentTimeMillis() - start);
    }



    //stream: 50 ms
    //parallelStream: 30 ms
    private static void runTaskTwo() {
        int max = 0;
        long start = 0L;
        start = System.currentTimeMillis();
        max = Arrays.stream(arr).max().orElse(0);
        System.out.format("(stream) Максимальное значение=%d, затраченное время=%d\n",
                max, System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        max = Arrays.stream(arr).parallel().max().orElse(0);
        System.out.format("(parallel) Максимальное значение=%d, затраченное время=%d\n",
                max, System.currentTimeMillis() - start);

    }

    //runTaskThree\\
    //RecursiveTask, когда if (this.data.length > 100_000: 1225
    //RecursiveTask (one thread), когда if (this.data.length > 100_000_000): 51, т.е. разделение потоков не было
    //main (one thread): 31
    //stream: 50 ms
    //parallelStream: 27 ms
    //Проводилось несколько тестов и во всех них наиболее быстрое решение предоставлял метод parallelStream.

    public static void main(String[] args) {
        runTaskOne();
        runTaskTwo();
    }
}
