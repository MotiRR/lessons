package org.vtb.lesson3.entity.participant;

public class Cat implements Participant {

    private String name;
    private int limitRun;
    private int limitJump;

    public Cat() {
    }

    public Cat(String name, int limitRun, int limitJump) {
        this.name = name;
        this.limitRun = limitRun;
        this.limitJump = limitJump;
    }

    @Override
    public boolean run(int runLength, String nameLet) {
        boolean success = limitRun > runLength;
        System.out.println(new StringBuilder().append(name).append(" ").append(success ? "Пробежал успешно" : "Не смог пробежать")
                .append(" ").append(nameLet).toString());
        return success;

    }

    @Override
    public boolean jump(int jumpHeight, String nameLet) {
        boolean success = limitJump > jumpHeight;
        System.out.println(new StringBuilder().append(name).append(" ").append(success ? "Перепрыгнул успешно" : "Не смог перепрыгнуть")
                .append(" ").append(nameLet).toString());
        return success;
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
