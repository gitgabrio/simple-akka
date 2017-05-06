package com.mytutorial;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 06/05/17.
 */
public class AkkaDriver extends AbstractActor {


    private ActorRef akkaBot;

    public AkkaDriver(ActorRef akkaBot) {
        this.akkaBot = akkaBot;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, this::feedback).build();
    }

    private void feedback(String string) {
        if (string.equals("DRIVE")) {
            startDrive();
        } else {
            System.out.println(getSender() + " told " + string);
        }
    }

    private void startDrive() {
        akkaBot.tell(new AkkaBot.Move(AkkaBot.Direction.FORWARD), getSelf());
        akkaBot.tell(new AkkaBot.Move(AkkaBot.Direction.BACKWARDS), getSelf());
        akkaBot.tell(new AkkaBot.Stop(), getSelf());
    }
}
