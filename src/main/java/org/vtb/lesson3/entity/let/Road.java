package org.vtb.lesson3.entity.let;

public class Road implements Let {

    private String name;
    private int length;

    public Road() {
    }

    public Road(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
