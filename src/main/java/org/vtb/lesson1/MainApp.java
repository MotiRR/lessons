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
    public static String isSign(int number) {
        if (number >= 0)
            return "Положительное число";
        else
            return "Отрицательное число";
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
    public static int[] swapZeroOne(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == 1) arr[i] = 0;
            else
                arr[i] = 1;
        return arr;
    }

    //    6. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 2 5 8 11 14 17 20 23;
    public static int[] fillArray(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            switch (i) {
                case 0:
                    arr[i] = 2;
                    break;
                case 1:
                    arr[i] = 5;
                    break;
                case 2:
                    arr[i] = 8;
                    break;
                case 3:
                    arr[i] = 11;
                    break;
                case 4:
                    arr[i] = 14;
                    break;
                case 5:
                    arr[i] = 17;
                    break;
                case 6:
                    arr[i] = 20;
                    break;
                case 7:
                    arr[i] = 23;
                    break;
            }
        return arr;
    }

    //    7. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    public static int[] multiplyTwo(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] < 6) arr[i] *= 2;
        return arr;
    }

    //    8. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов)
    //    заполнить его диагональные элементы единицами;
    public static int[][] fillDiagonals(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
            arr[arr.length - 1 - i][i] = 1;
        }

        return arr;
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
    public static String isLeapYear(int year) {
        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            return "Год " + year + " является високосным";
        } else {
            return "Год " + year + " не является високосным";
        }
    }

    //* 11. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
    // если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры:
    // checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true, граница
    // показана символами ||, эти символы в массив не входят.
    public static boolean sumLeftRight(int[] arr) {
        int left = 0, right = 0;
        for (int i = 1; i < arr.length; i++) {
            left = sumElements(Arrays.copyOfRange(arr, 0, arr.length - i));
            right = sumElements(Arrays.copyOfRange(arr, arr.length - i, arr.length));
            if (left == right)
                return true;
        }
        return false;
    }

    // Часть 11 задания
    public static int sumElements(int[] arr) {
        int sum = 0;
        for (int j = 0; j < arr.length; j++)
            sum += arr[j];
        return sum;
    }

    public static void printOnConsole(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void printOnConsole(int number) {
        System.out.println(number);
    }

    public static void printOnConsole(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printOnConsole(String str) {
        System.out.println(str);
    }

    public static void printOnConsole(boolean logicalNumber) {
        System.out.println(logicalNumber);
    }

    public static void main(String[] args) {
        int taskNumber = 0;
        printOnConsole("Введите номер задания");
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            taskNumber = in.nextInt();
        } else {
            printOnConsole("Необходимо ввести целочисленное число");
            return;
        }
        performTask(taskNumber);
    }

    public static void performTask(int taskNumber) {
        switch (taskNumber) {
            case 1:
                printOnConsole(computeSum(1, 10));
                break;
            case 2:
                printOnConsole(isSign(2));
                break;
            case 3:
                printOnConsole(isNegative(-32));
                break;
            case 4:
                printOnConsole(formString("Иван"));
                break;
            case 5:
                printOnConsole(swapZeroOne(new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0}));
                break;
            case 6:
                printOnConsole(fillArray(new int[8]));
                break;
            case 7:
                printOnConsole(multiplyTwo(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1}));
                break;
            case 8:
                int[][] arr = new int[8][8];
                printOnConsole(fillDiagonals(arr));
                break;
            case 9:
                printOnConsole(foundMinMax(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1}));
                break;
            case 10:
                printOnConsole(isLeapYear(2020));
                break;
            case 11:
                printOnConsole(sumLeftRight(new int[]{2, 2, 2, 1, 2, 2, 10, 1}));
                break;
            default:
                System.out.format("Нет задания с номером %d", taskNumber);
                break;
        }
    }
}
