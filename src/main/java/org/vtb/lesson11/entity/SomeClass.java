package org.vtb.lesson11.entity;


import org.vtb.lesson11.annotation.AfterSuite;
import org.vtb.lesson11.annotation.BeforeSuite;
import org.vtb.lesson11.annotation.Test;

public class SomeClass {

    @BeforeSuite
    public void before() {
        System.out.println("before");
    }

    @Test(4)
    public void test1() {
        System.out.println("1");
    }

    @Test(2)
    public void test2() {
        System.out.println("2");
    }

    @Test(2)
    private void test3() {
        System.out.println("2");
    }

    @AfterSuite
    public void after() {
        System.out.println("after");
    }
}
