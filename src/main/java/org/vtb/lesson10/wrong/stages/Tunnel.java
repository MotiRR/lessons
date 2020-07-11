package org.vtb.lesson10.wrong.stages;

import org.vtb.lesson10.wrong.Car;
import org.vtb.lesson10.wrong.Race;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private Semaphore smp;

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.smp = new Semaphore(Race.COMPETITORS_COUNT / 2);
    }

    @Override
    public void overcome(Car c) {
        try {
            try {
                smp.acquire();
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                smp.release();
                System.out.println(c.getName() + " закончил этап: " + description);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
