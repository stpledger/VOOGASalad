package engine.actions;

import authoring.entities.Entity;
import engine.components.Position;
import engine.components.Velocity;
import engine.components.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * This is the actions class which contains many methods that return consumers representing actions in the
 * form of lambdas to be used by the entities in the game
 *
 * @author cndracos
 */
public class Actions {

    /**
     * @param actor Entity moving left
     * @return left action
     */
    public Consumer left (Entity actor) {
        Velocity v = (Velocity) actor.get(Velocity.KEY);
        return (Serializable & Consumer) (e) -> v.setXVel(-30);
    }

    /**
     * @param actor Entity moving right
     * @return right action
     */
    public Consumer right (Entity actor) {
        Velocity v = (Velocity) actor.get(Velocity.KEY);
        return (Serializable & Consumer) (e) -> v.setXVel(30);
    }

    /**
     * @param actor Entity moving up
     * @return up action
     */
    public Consumer up (Entity actor) {
        Velocity v = (Velocity) actor.get(Velocity.KEY);
        return (Serializable & Consumer) (e) -> v.setYVel(-30);
    }

    /**
     * @param actor Entity moving down
     * @return down action
     */
    public Consumer down (Entity actor) {
        Velocity v = (Velocity) actor.get(Velocity.KEY);
        return (Serializable & Consumer) (e) -> v.setYVel(30);
    }

    /**
     * This would be an AI component that has an enemy follow you
     * @param followed Player/entity being followed
     * @param tracker  Enemy/entity tracking the followed
     * @return action which result in the tracker moving towards the followed
     */
    public Consumer followsYou (Entity followed, Entity tracker) {
        Position p = (Position) followed.get(Position.KEY);
        Position p2 = (Position) tracker.get(Position.KEY);
        Velocity v = (Velocity) tracker.get(Velocity.KEY);

        return (Serializable & Consumer) (time) -> {
            Double myTime = (Double) time;
            v.setXVel((p.getXPos() - p2.getXPos()) * myTime * 10); //change trackers x velocity towards the followed
            v.setYVel((p.getYPos() - p2.getYPos()) * myTime * 10); //change trackers y velocity towards the followed
        };
    }

    public Consumer patrol(Map<String, Component> actor, List<Position> coordinates) {
        Velocity v = (Velocity) actor.get(Velocity.KEY);
        Position p = (Position) actor.get(Position.KEY);

        AtomicReference<Position> destination = new AtomicReference<>(new Position(-1, coordinates.get(0).getXPos(), coordinates.get(0).getYPos()));
        AtomicInteger current = new AtomicInteger();

        return (Serializable & Consumer) (time) -> {
            v.setXVel((destination.get().getXPos()-p.getXPos())/distance(p, destination.get()) * 100);
            v.setYVel((destination.get().getYPos()-p.getYPos())/distance(p, destination.get()) * 100);
            if (distance(p, destination.get()) < 10) {
                if (current.get() == coordinates.size() - 1) current.set(0);
                else current.getAndIncrement();
                destination.set(coordinates.get(current.get()));
            }
        };

    }


    private static double distance (Position p1, Position p2) {
        return Math.sqrt(Math.pow(p1.getYPos()-p2.getYPos(), 2) + Math.pow(p1.getXPos() - p2.getXPos(), 2));
    }

}