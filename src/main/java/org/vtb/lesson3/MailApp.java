package org.vtb.lesson3;

import org.vtb.lesson3.entity.let.Let;
import org.vtb.lesson3.entity.let.Road;
import org.vtb.lesson3.entity.let.Wall;
import org.vtb.lesson3.entity.participant.Cat;
import org.vtb.lesson3.entity.participant.Human;
import org.vtb.lesson3.entity.participant.Participant;
import org.vtb.lesson3.entity.participant.Robot;

public class MailApp {

    public static boolean checkLet(Participant participant, Let let) {
        boolean run, jump;
        if (let instanceof Road) {
            run = participant.getLimitRun() > ((Road) let).getLength();
            System.out.printf("Участник %s %s препятствие %s\n", participant.getName(), participant.run(run), let.getName());
            return run;
        }
        if (let instanceof Wall) {
            jump = participant.getLimitJump() > ((Wall) let).getHeight();
            System.out.printf("Участник %s %s препятствие %s\n", participant.getName(), participant.jump(jump), let.getName());
            return jump;
        }
        return false;
    }

    public static void fillLets(Array<Let> lets) {
        lets.add(new Road("road 1", 230));
        lets.add(new Wall("wall 1", 10));
        lets.add(new Road("road 2", 150));
    }

    public static void fillParticipants(Array<Participant> participants) {
        participants.add(new Cat("cat", 250, 5));
        participants.add(new Human("human", 300, 15));
        participants.add(new Robot("robot", 400, 2));
    }

    public static void checkLets(Array<Participant> participants, Array<Let> lets) {

      for (Participant participant : participants) {
            for (Let let : lets) {
                if (!checkLet(participant, let)) break;
            }
        }
    }

    public static void main(String[] args) {
        Array<Let> lets = new Array<>(10);
        Array<Participant> participants = new Array<>(10);
        fillLets(lets);
        fillParticipants(participants);
        checkLets(participants, lets);
    }
}
