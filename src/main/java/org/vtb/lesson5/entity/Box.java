package org.vtb.lesson5.entity;

import java.util.ArrayList;

public class Box<F extends Fruit> {

    private ArrayList<F> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public Box(ArrayList<F> fruits) {
        this.fruits = fruits;
    }

    public double getWeight() {
        if(fruits.isEmpty()) return 0.0;
        return fruits.get(0).getWeigh() * fruits.size();
    }

    public boolean compare(Box<?> another) {
        return Math.abs(this.getWeight() - another.getWeight()) < 0.001;
    }

    public void moveFruit(Box<F> another) {
        another.getFruits().addAll(fruits);
        fruits.clear();
    }

    public void addFruit(F fruit) throws NullPointerException {
        fruits.add(fruit);
    }

    public ArrayList<F> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<F> fruits) {
        this.fruits = fruits;
    }


}
