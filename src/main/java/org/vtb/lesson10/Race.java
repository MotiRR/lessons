package org.vtb.lesson10;


import org.vtb.lesson10.stages.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Race {
    public static final int COMPETITORS_COUNT = 4;

    private List<Stage> stages;

    private List<Thread> threadCars = new ArrayList<>();

    public List<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public void begin() throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Car[] cars = new Car[COMPETITORS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(this, 20 + (int) (Math.random() * 10));
            threadCars.add(new Thread(cars[i]));
        }
        Car.setThread();
        for (Thread t : threadCars)
            t.start();
        Car.cdl.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        for (Thread t : threadCars)
            t.join();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
