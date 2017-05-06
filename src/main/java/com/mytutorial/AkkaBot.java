package com.mytutorial;

import akka.actor.AbstractActor;

import java.util.Optional;

public class AkkaBot extends AbstractActor {

    // Add the following inside AkkaBot

    private Optional<Direction> direction = Optional.empty();

    private boolean moving = false;


    public Receive createReceive() {
        return receiveBuilder()
                .match(Move.class, this::onMove)
                .match(Stop.class, this::onStop)
                .build();
    }

    private void onMove(Move move) {
        moving = true;
        direction = Optional.of(move.direction);
        System.out.println("I am now moving " + direction.get());
        getSender().tell("I am now moving " + direction.get(), getSelf());
    }

    private void onStop(Stop stop) {
        moving = false;
        System.out.println("I stopped moving");
    }

    // Inside AkkaBot.java code add the following inner classes

    public enum Direction {FORWARD, BACKWARDS, RIGHT, LEFT}

    public static class Move {

        public final Direction direction;

        public Move(Direction direction) {
            this.direction = direction;
        }
    }

    public static class Stop {
    }

    public static class GetRobotState {
    }

    public static class RobotState {

        public final Direction direction;

        public final boolean moving;

        public RobotState(Direction direction, boolean moving) {
            this.direction = direction;
            this.moving = moving;
        }
    }

}