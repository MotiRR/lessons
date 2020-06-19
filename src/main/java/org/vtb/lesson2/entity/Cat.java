package org.vtb.lesson2.entity;

public class Cat implements Animal{

    private String name;
    private boolean isSwim;

    public Cat(String name, boolean isSwim) {
        this.name = name;
        this.isSwim = isSwim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSwim() {
        return isSwim;
    }

    public void setSwim(boolean swim) {
        isSwim = swim;
    }

    @Override
    public String run(int length) {
        return name + " пробежала "+ length + " м.";
    }

    @Override
    public String swim(int length) {
        return isSwim ? name + " проплыла "+ length + " м." : name + " не умеет плавать ";
    }
}
