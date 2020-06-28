package org.vtb.lesson5.entity;

public class Apple extends Fruit {

    private final double weigh = 1.0f;

    public Apple() {}

    public Apple(String kind) {
        super(kind);
    }

    @Override
    public double getWeigh() {
        return weigh;
    }
}
