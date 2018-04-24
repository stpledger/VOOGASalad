package engine.actions;

import authoring.entities.Entity;
import authoring.entities.Player;
import engine.components.Position;
import engine.components.Velocity;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
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
        return (Serializable & Consumer) (e) -> v.setXVel(-10);
    }

    /**
     * @param actor Entity moving right
     * @return right action
     */
    public Consumer right (Entity actor) {
        Velocity v = (Velocity) actor.get(Velocity.KEY);
        return (Serializable & Consumer) (e) -> v.setXVel(10);
    }

    /**
     * @param actor Entity moving up
     * @return up action
     */
    public Consumer up (Entity actor) {
        Velocity v = (Velocity) actor.get(Velocity.KEY);
        return (Serializable & Consumer) (e) -> v.setYVel(-10);
    }

    /**
     * @param actor Entity moving down
     * @return down action
     */
    public Consumer down (Entity actor) {
        Velocity v = (Velocity) actor.get(Velocity.KEY);
        return (Serializable & Consumer) (e) -> v.setYVel(10);
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

}