package org.vtb.lesson7;

import org.vtb.lesson6.entity.Phonebook;
import org.vtb.lesson7.entity.MyEntry;

import java.io.InputStream;
import java.util.*;

public class MainApp {

    static final Random random = new Random();

    //* 10000 обращений
    //             10     100     10000    100000
    //ArrayList:    1      1        1        1
    //LinkedList:   2      2        70      983
    private static void runTaskOne() {

        List<Long> listTime = new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
        fillAllArrayList(lists, "array", 10);
        fillAllArrayList(lists, "array", 100);
        fillAllArrayList(lists, "array", 10000);
        fillAllArrayList(lists, "array", 100000);
        checkList(listTime, lists);
        System.out.println("Array:");
        for(long time: listTime)
            System.out.println(time);

        lists.clear();
        listTime.clear();

        fillAllArrayList(lists, "linked", 10);
        fillAllArrayList(lists, "linked", 100);
        fillAllArrayList(lists, "linked", 10000);
        fillAllArrayList(lists, "linked", 100000);
        checkList(listTime, lists);
        System.out.println("Linked:");
        for(long time: listTime)
            System.out.println(time);
    }

    private static void checkList(List<Long> listTime, List<List<Integer>> lists) {
        int appeal = 10000;
        for (int i = 0; i < lists.size(); i++) {
            int size = lists.get(i).size();
            listTime.add(System.currentTimeMillis());
            for (int j = 0; j < appeal; j++) {
                lists.get(i).get(size / 2);
            }
            listTime.set(i, System.currentTimeMillis() - listTime.get(i));
        }
    }

    private static void fillAllArrayList(List<List<Integer>> lists, String typeList, int count) {

        if(typeList.equalsIgnoreCase("array")) {
            List<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < count; i++)
                arrayList.add(i);
            lists.add(arrayList);
        }

        if(typeList.equalsIgnoreCase("linked")) {
            List<Integer> arrayList = new LinkedList<>();
            for (int i = 0; i < count; i++)
                arrayList.add(i);
            lists.add(arrayList);
        }
    }

    //             10     100     10000    100000
    //ArrayList:    0      0        2       112
    //LinkedList:   0      0        30      2729
    private static void runTaskTwo() {
        List<Long> listTime = new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();

        fillAllArrayList(lists, "linked", 10);
        fillAllArrayList(lists, "linked", 100);
        fillAllArrayList(lists, "linked", 10000);
        fillAllArrayList(lists, "linked", 100000);
        removeList(listTime, lists);
        System.out.println("Linked:");
        for(long time: listTime)
            System.out.println(time);

        lists.clear();
        listTime.clear();

        fillAllArrayList(lists, "array", 10);
        fillAllArrayList(lists, "array", 100);
        fillAllArrayList(lists, "array", 10000);
        fillAllArrayList(lists, "array", 100000);
        removeList(listTime, lists);
        System.out.println("Array:");
        for(long time: listTime)
            System.out.println(time);
    }

    private static void removeList(List<Long> listTime, List<List<Integer>> lists) {
        int size = 0, sizeTwo = 0, middle = 0;
        for (int i = 0; i < lists.size(); i++) {
            size = lists.get(i).size();
            listTime.add(System.currentTimeMillis());
            for (int j = size / 2; j > 0 ; j--) {
                sizeTwo = lists.get(i).size();
                middle = sizeTwo / 2;
                lists.get(i).remove(middle);
            }
            listTime.set(i, System.currentTimeMillis() - listTime.get(i));
        }
    }

    //Average time HashMap: 1.8E-4
    //Average time ArrayList: 0.11711
    private static void runTaskThree() {
        int fillLength = 50000;
        int appeal = 100000;
        int key = 0;
        long allTimeHashMap = 0, allTimeList = 0, time = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        List<MyEntry<Integer, Integer>> list = new ArrayList<>();
        fillStructure(hashMap, list, fillLength);
        for (int i = 0; i < appeal; i++) {
            key = random.nextInt(fillLength);

            time = System.currentTimeMillis();
            hashMap.get(key);
            allTimeHashMap = System.currentTimeMillis() - time + allTimeHashMap;

            time = System.currentTimeMillis();
            for (MyEntry<Integer, Integer> el : list) {
                if (el.getKey() == key) break;
            }
            allTimeList = System.currentTimeMillis() - time + allTimeList;
        }
        System.out.println("Average time HashMap: " + ((double) allTimeHashMap / appeal));
        System.out.println("Average time ArrayList: " + ((double) allTimeList / appeal));
    }

    private static void fillStructure(HashMap<Integer, Integer> hashMap, List<MyEntry<Integer,
            Integer>> list, int fillLength) {
        int value = 0;
        for (int i = 0; i < fillLength; i++) {
            value = random.nextInt();
            hashMap.put(i, value);
            list.add(new MyEntry<>(i, value));
        }
    }

    public static void main(String[] args) {
        runTaskOne();
        runTaskTwo();
        runTaskThree();
    }
}
