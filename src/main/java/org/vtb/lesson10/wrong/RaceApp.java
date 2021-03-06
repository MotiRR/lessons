package org.vtb.lesson10.wrong;


import org.vtb.lesson10.wrong.stages.Road;
import org.vtb.lesson10.wrong.stages.Tunnel;

public class RaceApp {
    public static void main(String[] args) {
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        try {
            race.begin();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
