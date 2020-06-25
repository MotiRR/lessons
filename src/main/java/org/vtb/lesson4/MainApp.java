package org.vtb.lesson4;


import org.vtb.lesson4.exception.MyArrayDataException;
import org.vtb.lesson4.exception.MyArraySizeException;

import java.util.Arrays;

public class MainApp {


    private static int calculateAmount(String[][] strings) throws MyArraySizeException {
        if (strings.length != 4) throw new MyArraySizeException();
        for (String[] string : strings) if (string.length != 4) throw new MyArraySizeException();
        int sum = 0;
        for (int i = 0; i < strings.length; i++)
            for (int j = 0; j < strings[i].length; j++)
                try {
                    sum += Integer.parseInt(strings[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("В матрице должны быть числовые значения, а получено: \"%s\" расположенная по " +
                            "индексам: строка = %d, столбец = %d", strings[i][j], i, j));
                }
        return sum;
    }

    private static String[][] fillMatrix(boolean errSize, boolean errData) {
        String[][] strings = new String[4][4];
        if (errSize) strings = new String[3][5];
        for (String[] string : strings) Arrays.fill(string, "1");
        if (errData) strings[1][1] = "ошибочка";
        return strings;
    }

    public static void main(String[] args) {
        String[][] strings = fillMatrix(false, false);
        try {
            System.out.println(calculateAmount(strings));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Результат не получен!");
            e.printStackTrace();
        }
    }
}
