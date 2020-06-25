package org.vtb.lesson3.entity.let;

import org.vtb.lesson3.entity.participant.Participant;

public class Road implements Let {

    private String name;
    private int length;

    public Road() {
    }

    public Road(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean interact(Participant participant) {
        return participant.run(length, name);
    }
}
