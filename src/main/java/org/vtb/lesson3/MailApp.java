package org.vtb.lesson3;

import org.vtb.lesson3.entity.let.Let;
import org.vtb.lesson3.entity.let.Road;
import org.vtb.lesson3.entity.let.Wall;
import org.vtb.lesson3.entity.participant.Cat;
import org.vtb.lesson3.entity.participant.Human;
import org.vtb.lesson3.entity.participant.Participant;
import org.vtb.lesson3.entity.participant.Robot;

public class MailApp {

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
                if (!let.interact(participant)) break;
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
