package org.vtb.lesson2.entity;

public class Dog implements Animal{

    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String run(int length) {
        return name + " пробежала "+ length + " м.";
    }

    @Override
    public String swim(int length) {
        return name + " проплыла "+ length + " м.";
    }
}
