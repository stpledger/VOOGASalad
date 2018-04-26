package engine.actions;

import authoring.entities.Entity;
import authoring.entities.Player;
import engine.components.XPosition;
import engine.components.YPosition;
import engine.components.XVelocity;
import engine.components.YVelocity;
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

}