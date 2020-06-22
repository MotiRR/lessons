package org.vtb.lesson2.entity;

public abstract class  Animal {
    public static int count = 0;
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String run(int length) {
        return name + " пробежал " + length + " м.";
    }

    public String swim(int length) {
        return name + " проплыл "+ length + " м.";
    }
}
