package engine.actions;

import authoring.entities.Entity;

import authoring.entities.Player;
import engine.components.Component;
import engine.components.XPosition;
import engine.components.YPosition;
import engine.components.XVelocity;
import engine.components.YVelocity;
import engine.components.groups.Position;
import engine.components.groups.Velocity;

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
        XVelocity v = (XVelocity) actor.get(XVelocity.KEY);
        return (Serializable & Consumer) (e) -> v.setData(-10);
    }

    /**
     * @param actor Entity moving right
     * @return right action
     */

    public Consumer right (Entity actor) {
        XVelocity v = (XVelocity) actor.get(XVelocity.KEY);
        return (Serializable & Consumer) (e) -> v.setData(10);
    }

    /**
     * @param actor Entity moving up
     * @return up action
     */

    public Consumer up (Entity actor) {
        YVelocity v = (YVelocity) actor.get(YVelocity.KEY);
        return (Serializable & Consumer) (e) -> v.setData(-10);
    }

    /**
     * @param actor Entity moving down
     * @return down action
*/
    public Consumer down (Entity actor) {
        YVelocity v = (YVelocity) actor.get(YVelocity.KEY);
        return (Serializable & Consumer) (e) -> v.setData(10);
    }

    /**
     * This would be an AI component that has an enemy follow you
     * @param followed Player/entity being followed
     * @param tracker  Enemy/entity tracking the followed
     * @return action which result in the tracker moving towards the followed
     */
    public Consumer followsYou (Entity followed, Entity tracker) {
        XPosition px = (XPosition) followed.get(XPosition.KEY);
        YPosition py = (YPosition) followed.get(YPosition.KEY);
        XPosition tx = (XPosition) tracker.get(XPosition.KEY);
        YPosition ty = (YPosition) followed.get(YPosition.KEY);
        XVelocity vx = (XVelocity) tracker.get(XVelocity.KEY);
        YVelocity vy = (YVelocity) tracker.get(YVelocity.KEY);

        return (Serializable & Consumer) (time) -> {
            Double myTime = (Double) time;
            vx.setData((px.getData() - tx.getData()) * myTime * 10);
            vy.setData((py.getData() - ty.getData()) * myTime * 10);
        };
    }

    /**
     * A patrol action to be given to an AI component for enemies, blocks, etc. Goes to the given coordinates in
     * order then repeats.
     *
     * @param actor The entity moving around
     * @param coordinates The positions the entity will visit
     * @return the actions which performs this method
     */
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
        return Math.sqrt(Math.pow(p1.getYPos()-p2.getYPos(), 2) + Math.pow(p1.getXPos() - p2.getXPos(), 2)); //distance between two positions/points
    }

}