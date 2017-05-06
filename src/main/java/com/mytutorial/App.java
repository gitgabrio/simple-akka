package com.mytutorial;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        final ActorSystem system = ActorSystem.create();
        final ActorRef akkaBot = system.actorOf(Props.create(AkkaBot.class), "akkaBot");
        final ActorRef akkaDriver = system.actorOf(Props.create(AkkaDriver.class, akkaBot), "akkaDriver");
        akkaDriver.tell("DRIVE", ActorRef.noSender());
//        akkaBot.tell(new AkkaBot.Move(AkkaBot.Direction.FORWARD), ActorRef.noSender());
//        akkaBot.tell(new AkkaBot.Move(AkkaBot.Direction.BACKWARDS),ActorRef.noSender());
//        akkaBot.tell(new AkkaBot.Stop(),ActorRef.noSender());
    }
}
