package org.vtb.lesson3.entity.let;

import org.vtb.lesson3.entity.participant.Participant;

public class Wall implements Let {

    private String name;
    private int height;

    public Wall() {
    }

    public Wall(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean interact(Participant participant) {
        return participant.jump(height, name);
    }
}
