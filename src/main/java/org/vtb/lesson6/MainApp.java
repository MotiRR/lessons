package org.vtb.lesson6;

import org.vtb.lesson6.entity.Phonebook;

import java.util.*;

public class MainApp {

    private static void runTaskOne() {
        HashMap<String, Integer> map = new HashMap<>();
        List<String> list = List.of("привет", "пока", "привет", "brain", "geek",
                "vtb", "здравствуйте", "прощайте", "brain", "vtb");
        Set<String> uniq = new HashSet<>(list);
        for (String el : list)
            if (!map.containsKey(el))
                map.put(el, 1);
            else {
                map.put(el, map.get(el) + 1);
                uniq.remove(el);
            }
        System.out.println("Уникальные значения:");
        for (String el : uniq)
            System.out.println(el);
        System.out.println("Список:");
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            System.out.printf("%s : %d \n", item.getKey(), item.getValue());
        }
    }


    private static void runTaskTwo() {
        Phonebook phonebook = new Phonebook();
        phonebook.add("ФИО_1", "98797");
        phonebook.add("ФИО_1", "231436");
        phonebook.add("ФИО_2", "0878790");
        phonebook.add("ФИО_2", "65462");
        phonebook.add("ФИО_2", "166");
        System.out.println("ФИО_1: " + phonebook.get("ФИО_1"));
        System.out.println("ФИО_2: " + phonebook.get("ФИО_2"));
    }

    public static void main(String[] args) {
        runTaskOne();
        runTaskTwo();
    }
}
