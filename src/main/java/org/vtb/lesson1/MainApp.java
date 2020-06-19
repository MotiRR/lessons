package org.vtb.lesson1;

import java.util.Arrays;
import java.util.Scanner;

public class MainApp {
    
    //    1. Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах от 10 до 20
    //    (включительно), если да – вернуть true, в противном случае – false.
    public static boolean computeSum(int first, int second) {
        int sum = first + second;
        return sum >= 10 && sum <= 20;
    }

    //    2. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
    //    положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
    public static boolean isSign(int number) {
        return number >= 0;
    }

    //    3. Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true, если число
    //    отрицательное.
    public static boolean isNegative(int number) {
        return number < 0;
    }

    //    4. Написать метод, которому в качестве параметра передается строка, обозначающая имя. Метод должен вывести в консоль
    //    сообщение «Привет, указанное_имя!».
    public static String formString(String name) {
        return "Привет, " + name + "!";
    }

    //    5. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    //    С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static void swapZeroOne(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = 1 - arr[i];
    }

    //    6. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 2 5 8 11 14 17 20 23;
    public static void fillArray(int[] arr) {
        arr[0] = 2;
        for (int i = 0; i < arr.length; i++)
            arr[i] = arr[i] + 3;
    }

    //    7. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    public static void multiplyTwo(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] < 6) arr[i] *= 2;
    }

    //    8. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов)
    //    заполнить его диагональные элементы единицами;
    public static void fillDiagonals(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
            arr[arr.length - 1 - i][i] = 1;
        }
    }

    // *9. Задать одномерный массив и найти в нем минимальный и максимальный элементы;
    public static String foundMinMax(int[] arr) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int value : arr) {
            if (value > max) max = value;
            if (value < min) min = value;
        }
        return "Максимальный элемент = " + max + " Минимальный элемент = " + min;
    }

    //* 10. Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль. Каждый
    // 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    public static boolean isLeapYear(int year) {
        return (year % 400 == 0 || (year % 4 == 0) && (year % 100 != 0));
    }

    //* 11. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
    // если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры:
    // checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true, граница
    // показана символами ||, эти символы в массив не входят.
    public static boolean sumLeftRight(int[] arr) {
        int sum = 0;
        int left = 0, right = 0;
        for (int value : arr) sum = sum + value;
        for (int i = 1; i < arr.length; i++) {
            left += arr[i - 1];
            right = sum - left;
            if (left == right)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int taskNumber = 0;
        System.out.println("Введите номер задания");
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            taskNumber = in.nextInt();
        } else {
            System.out.println("Необходимо ввести натуральное число");
            return;
        }
        performTask(taskNumber);
    }


    public static void performTask(int taskNumber) {
        switch (taskNumber) {
            case 1:
                System.out.println(computeSum(1, 10));
                break;
            case 2:
                System.out.println(isSign(2));
                break;
            case 3:
                System.out.println(isNegative(-32));
                break;
            case 4:
                System.out.println(formString("Иван"));
                break;
            case 5:
                int[] arr = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
                swapZeroOne(arr);
                System.out.println(Arrays.toString(arr));
                break;
            case 6:
                int[] arrTwo = new int[8];
                fillArray(arrTwo);
                System.out.println(Arrays.toString(arrTwo));
                break;
            case 7:
                int[] arrThree = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
                multiplyTwo(arrThree);
                System.out.println(Arrays.toString(arrThree));
                break;
            case 8:
                int[][] matrix = new int[8][8];
                fillDiagonals(matrix);
                System.out.println(Arrays.deepToString(matrix));
                break;
            case 9:
                int[] arrFour = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
                System.out.println(foundMinMax(arrFour));
                break;
            case 10:
                System.out.println(isLeapYear(2020));
                break;
            case 11:
                int[] arrFive = new int[]{2, 2, 2, 1, 2, 2, 10, 1};
                System.out.println(sumLeftRight(arrFive));
                System.out.println(Arrays.toString(arrFive));
                break;
            default:
                System.out.format("Нет задания с номером %d", taskNumber);
                break;
        }
    }
}
