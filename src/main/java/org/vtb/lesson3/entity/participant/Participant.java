package org.vtb.lesson3.entity.participant;

public interface Participant {
    String run(boolean run);

    String jump(boolean jump);

    String getName();

    int getLimitJump();

    int getLimitRun();

}
