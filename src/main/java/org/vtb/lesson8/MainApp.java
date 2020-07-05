package org.vtb.lesson8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainApp {

    static final Random random = new Random();

    /*
     * Взять строку, состоящую из 100 слов разделенных пробелом, получить список слов длиннее 5 символов, и
     * склеить их в одну строку с пробелом в качестве разделителя;
     */
    private static void runTaskOne() {

        Stream<String> streamFromGenerate = Stream.generate(() -> {
                    int r = random.nextInt(10);
                    if (r == 0) return "geek";
                    if (r == 1) return "vtb";
                    if (r == 2) return "brains";
                    if (r == 3) return "students";
                    if (r == 4) return "intern";
                    if (r == 5) return "java";
                    if (r == 6) return "spring";
                    if (r == 7) return "maven";
                    if (r == 8) return "kafka";
                    return "kubernetes";
                }
        );

        String[] str = streamFromGenerate.limit(100).reduce((x, y) -> (x + " " + y)).orElse("").split(" ");
        String string = Arrays.stream(str).filter(s -> s.length() > 5)
                .reduce((x, y) -> (x + " " + y)).orElse("");
        System.out.println("Склеиные строки длинее 5 символов: " + string);
    }

    //Найти список уникальных слов в двумерном массиве размером 5х5;
    private static void runTaskTwo() {
        String[][] matrix = new String[5][5];
        fillMatrix(matrix);
        List<String> list = new ArrayList<>();
        for (String[] row : matrix)
            list.addAll(Arrays.asList(row));
        Object[] arr = list.stream()
                .collect(Collectors.groupingBy(string -> string))
                .values()
                .stream()
                .filter(s -> s.size() == 1)
                .map(s -> s.get(0))
                .toArray();
        System.out.println("Список уникальных слов в двумерном массиве размером 5х5 = " + Arrays.toString(arr));
    }

    private static void fillMatrix(String[][] matrix) {
        for (String[] strings : matrix) Arrays.fill(strings, "geek");
        matrix[0][1] = "vtb";
        matrix[1][0] = "java";
        matrix[1][1] = "java";
    }

    //Посчитать сумму четных чисел в пределах от 100 до 200 (включительно);
    private static void runTaskThree() {
        IntStream stream = IntStream.rangeClosed(0, 300);
        int sum = stream.filter(i -> (i >= 100 && i <= 200 && i % 2 == 0))
                .reduce((Integer::sum)).orElse(0);
        System.out.println("Сумма четных чисел в пределах от 100 до 200 (включительно) = " + sum);
    }

    //Посчитать суммарную длину строк в одномерном массиве;
    private static void runTaskFour() {
        int lengthStrings = List.of("vtb", "geek", "maybe")
                .stream()
                .mapToInt(String::length)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println("Суммарная длина строк = " + lengthStrings);
    }

    //Из массива слов получить первые три слова в алфавитном порядке;
    private static void runTaskFive() {
        int[] count = new int[3];
        List<String> words = new ArrayList<>(Arrays.asList("fafg", "B", "Aa", "Aaaa", "Ab",  "dddd"));
        List<String> sortedWords = new ArrayList<>(words.stream().sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new)));
        for (int i = 0, j = 0; i < words.size(); i++, j++) {
            String o = words.get(i);
            count[j] = sortedWords.indexOf(o);
            if (j == 2 && count[0] == count[1] - 1 && count[1] == count[2] - 1) {
                for (int index : count)
                    System.out.println(sortedWords.get(index));
                j = 0;
                break;
            } else if (j == 2) {
                j = 0;
                --i;
                count[j] = sortedWords.indexOf(words.get(i));
            }
        }
    }

    public static void main(String[] args) {
//        runTaskOne();
//        runTaskTwo();
//        runTaskThree();
//        runTaskFour();
        runTaskFive();
    }
}
