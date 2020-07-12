package org.vtb.lesson11;

import org.vtb.lesson11.entity.SomeClass;
import org.vtb.lesson11.entity.Tester;

public class MainApp {

    public static void main(String[] args) {
        try {
            Tester.start(SomeClass.class, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
