package org.vtb.lesson2.entity;

public class Tiger implements Animal {

    private String name;

    public Tiger(String name) {
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
        return name + " пробежал " + length + " м.";
    }

    @Override
    public String swim(int length) {
        return name + " проплыл "+ length + " м.";
    }
}
