package org.vtb.lesson5.entity;

public class Orange extends Fruit {

    private final double weigh = 1.5f;

    public Orange() {
    }

    public Orange(String kind) {
        super(kind);
    }

    @Override
    public double getWeigh() {
        return weigh;
    }
}
