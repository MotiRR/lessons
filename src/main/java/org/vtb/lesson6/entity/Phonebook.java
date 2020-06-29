package org.vtb.lesson6.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Phonebook {

    private HashMap<String, List<String>> map;

    public Phonebook() {
        map = new HashMap<>();
    }

    public Phonebook(int capacity) {
        map = new HashMap<>(capacity);
    }

    public Phonebook(HashMap<String, List<String>> map) {
        this.map = map;
    }

    public HashMap<String, List<String>> getMap() {
        return map;
    }

    public void setMap(HashMap<String, List<String>> map) {
        this.map = map;
    }

    public void add(String nameOfMan, String phone) {
        if (map.containsKey(nameOfMan)) {
            map.get(nameOfMan).add(phone);
            map.put(nameOfMan, map.get(nameOfMan));
        } else {
            map.put(nameOfMan, new ArrayList<>(Collections.singletonList(phone)));
        }
    }

    public List<String> get(String nameOfMan) {
        return map.get(nameOfMan);
    }
}
