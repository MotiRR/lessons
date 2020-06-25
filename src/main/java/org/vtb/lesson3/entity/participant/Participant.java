package org.vtb.lesson3.entity.participant;

public interface Participant {
    boolean run(int runLength, String nameLet);

    boolean jump(int jumpHeight, String nameLet);

    String getName();

    int getLimitJump();

    int getLimitRun();

}
