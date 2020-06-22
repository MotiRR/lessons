package org.vtb.lesson3.entity.participant;

public class Robot implements Participant {

    private String name;
    private int limitRun;
    private int limitJump;

    public Robot() {
    }

    public Robot(String name, int limitRun, int limitJump) {
        this.name = name;
        this.limitRun = limitRun;
        this.limitJump = limitJump;
    }

    @Override
    public String run(boolean run) {
        return run ? "Пробежал успешно" : "Не смог пробежать";
    }

    @Override
    public String jump(boolean jump) {
        return jump ? "Перепрыгнул успешно" : "Не смог перепрыгнуть";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLimitRun() {
        return limitRun;
    }

    public void setLimitRun(int limitRun) {
        this.limitRun = limitRun;
    }

    @Override
    public int getLimitJump() {
        return limitJump;
    }

    public void setLimitJump(int limitJump) {
        this.limitJump = limitJump;
    }
}
