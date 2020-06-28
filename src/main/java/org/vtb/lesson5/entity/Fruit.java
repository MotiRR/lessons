package org.vtb.lesson5.entity;

public abstract class Fruit {
    protected String kind;
    protected double weigh;

    public Fruit(){}

    public Fruit(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public double getWeigh() {
        return weigh;
    }

    public void setWeigh(double weigh) {
        this.weigh = weigh;
    }
}
