package org.vtb.lesson10;

import org.vtb.lesson10.stages.Stage;
import org.vtb.lesson10.stages.Tunnel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {


    public static CountDownLatch cdl = new CountDownLatch(1);

    private static int CARS_COUNT;

    private Race race;
    private int speed;
    private String name;

    private Lock lock = new ReentrantLock();
    private static AtomicBoolean win = new AtomicBoolean(true);

    private static CyclicBarrier cb;
    private static Semaphore smp;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public static void setThread() {
        cb = new CyclicBarrier(CARS_COUNT);
        smp = new Semaphore(CARS_COUNT / 2);
    }

    @Override
    public void run() {

        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
            cdl.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            Stage stage = race.getStages().get(i);
            if(stage instanceof Tunnel) {
                try {
                    smp.acquire();
                    stage.overcome(this);
                } catch (InterruptedException e) {
                    System.out.format("Exception : %s", e.getMessage());
                } finally {
                    smp.release();
                }
            }
            stage.overcome(this);
        }

        if (lock.tryLock() && win.get()) {
            win.set(false);
            System.out.println("WIN " + this.name);
            lock.unlock();
        }
    }
}