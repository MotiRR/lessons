package org.vtb.lesson11.entity;

import org.vtb.lesson11.annotation.AfterSuite;
import org.vtb.lesson11.annotation.BeforeSuite;
import org.vtb.lesson11.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Tester {


    private static String setParameters(Method method) {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(method.getName());
        stringBuilder.append(" ");
        stringBuilder.append(method.getModifiers());
       /* for(Parameter par : method.getParameters())
            stringBuilder.append(" ").append(par.getType().getSimpleName());*/
        return stringBuilder.toString();
    }

    private static void setMap(Map hashMap, int value, String par, String type) {
        ArrayList<String> list = (ArrayList<String>) hashMap.getOrDefault(value, new ArrayList<>());
        if ((type.equalsIgnoreCase("after") && list.size() > 0) ||
                (type.equalsIgnoreCase("before") && list.size() > 0))
            throw new RuntimeException("Аннотация AfterSuite и BeforeSuite могуть использоваться лишь " +
                    "для одного метода в классе");
        list.add(par);
        hashMap.put(value, list);
    }

    public static void start(Class testClass, boolean privateMethod) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object o = testClass.newInstance();
        Method[] methods = testClass.getDeclaredMethods();
        Map<Integer, List<String>> hashMap = new TreeMap<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                if (test.value() > 10 || test.value() < 1) {
                    System.out.println("Аннотация \"Тест\" может принимать " +
                            "значения от 1 до 10, метод: " + method.getName() + " не будет вызван");
                    continue;
                }
                setMap(hashMap, test.value(), setParameters(method), "test");
            }
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                setMap(hashMap, 0, setParameters(method), "before");
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                setMap(hashMap, methods.length + 1, setParameters(method), "after");
            }
        }

        for (Map.Entry entry : hashMap.entrySet()) {
            for (Object method : (List) entry.getValue()) {
                //par[0] - название метода
                //par[1] - модификатор доступа
                String[] par = method.toString().split(" ");
                // если необходимо запустить и приватные методы
                if (privateMethod) {
                    Method m = testClass.getDeclaredMethod(par[0]);
                    m.setAccessible(true);
                    m.invoke(o);
                    continue;
                }
                if (!par[1].equalsIgnoreCase("2"))
                    testClass.getMethod(par[0]).invoke(o);
            }

        }
    }
}
