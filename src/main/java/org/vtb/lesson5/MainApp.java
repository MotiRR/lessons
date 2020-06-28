package org.vtb.lesson5;


import org.vtb.lesson5.entity.Apple;
import org.vtb.lesson5.entity.Box;
import org.vtb.lesson5.entity.Orange;

import java.util.ArrayList;
import java.util.Arrays;

public class MainApp {


    private static <T> void swap(T[] arr, int i, int j) throws ArrayIndexOutOfBoundsException {
        T temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    private static <T> ArrayList newArrayList(T[] arr) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T el : arr)
            arrayList.add(el);
        return arrayList;
    }

    private static <T> Box createBoxWithFruits(T nameClass, int n) {
        ArrayList<T> fruits = null;
        if (nameClass == Apple.class) {
            fruits = new ArrayList();
            for (int i = 0; i < n; i++)
                fruits.add((T) new Apple());
        }

        if (nameClass == Orange.class) {
            fruits = new ArrayList();
            for (int i = 0; i < n; i++)
                fruits.add((T) new Orange());
        }
        return new Box(fruits);
    }

    private static void runTaskOne() {
        System.out.println("Задача 1");
        Integer[] n = new Integer[]{1, 2, 3, 4};
        swap(n, 1, 3);
        System.out.println(Arrays.toString(n));
    }

    private static void runTaskTwo() {
        System.out.println("Задача 2");
        Double[] n = new Double[]{1.0, 2.0, 3.0, 4.0};
        System.out.println(newArrayList(n).toString());
    }

    private static void runTaskThree() {
        System.out.println("Задача 3");
        Box<Apple> box1 = createBoxWithFruits(Apple.class, 3);
        Box<Apple> box2 = createBoxWithFruits(Apple.class, 5);
        Box<Orange> box3 = createBoxWithFruits(Orange.class, 2);
        System.out.printf("Ящик 1 (яблоки), количество: %d вес: %.1f\n", box1.getFruits().size(), box1.getWeight());
        System.out.printf("Ящик 2 (яблоки), количество: %d вес: %.1f\n", box2.getFruits().size(), box2.getWeight());
        System.out.printf("Ящик 3 (апельсины), количество: %d вес: %.1f\n", box3.getFruits().size(), box3.getWeight());

        System.out.println("Сравнение по весу 1 и 2 ящика: " + box1.compare(box2));
        System.out.println("Сравнение по весу 1 и 3 ящика: " + box1.compare(box3));

        System.out.println("Пересып из 1 первого ящика во 2 ... ");
        box1.moveFruit(box2);
        System.out.println("Количество яблок в 1 ящике: " + box1.getFruits().size());
        System.out.println("Количество яблок во 2 ящике: " + box2.getFruits().size());
    }

    public static void main(String[] args) {
        runTaskOne();
        runTaskTwo();
        runTaskThree();
    }

}
