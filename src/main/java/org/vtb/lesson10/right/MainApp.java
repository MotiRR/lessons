package org.vtb.lesson10.right;

import org.vtb.lesson10.right.stages.Road;
import org.vtb.lesson10.right.stages.Tunnel;

public class MainApp {

    public static void main(String[] args) {
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        race.begin();
    }
}
