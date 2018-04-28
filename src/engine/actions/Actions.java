package engine.actions;

import authoring.entities.Entity;

import engine.components.Component;
import engine.components.Player;
import engine.components.Score;
import engine.components.XAcceleration;
import engine.components.XPosition;
import engine.components.YPosition;
import engine.components.XVelocity;
import engine.components.YAcceleration;
import engine.components.YVelocity;
import engine.components.groups.Position;
import engine.components.groups.Velocity;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * This is the actions class which contains many methods that return consumers representing actions in the
 * form of lambdas to be used by the entities in the game
 *
 * @author cndracos
 */
public class Actions {

    /**
     * @param speed Entity moving left
     * @return left action
     */
	@SuppressWarnings("unchecked")
	public static Consumer<Map<String, Component>> moveLeft (double speed) {
    	return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
    		if(actor != null && (actor instanceof Map<?,?>)) {
    			if(actor.containsKey(XVelocity.KEY)) {
    				XVelocity xv = (XVelocity) actor.get(XVelocity.KEY);
    				xv.setData(-speed);
    			}
    		}
    	};
    }
	
	@SuppressWarnings("unchecked")
	public static Consumer<Map<String, Component>> moveRight (double speed) {
    	return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
    		if(actor != null && (actor instanceof Map<?,?>)) {
    			if(actor.containsKey(XVelocity.KEY)) {
    				XVelocity xv = (XVelocity) actor.get(XVelocity.KEY);
    				xv.setData(speed);
    			}
    		}
    	};
    }
	
	@SuppressWarnings("unchecked")
	public static Consumer<Map<String, Component>> moveUp (double speed) {
    	return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
    		if(actor != null && (actor instanceof Map<?,?>)) {
    			if(actor.containsKey(YVelocity.KEY)) {
    				YVelocity yv = (YVelocity) actor.get(YVelocity.KEY);
    				yv.setData(-speed);
    			}
    		}
    	};
    }
	
	@SuppressWarnings("unchecked")
	public static Consumer<Map<String, Component>> moveDown (double speed) {
    	return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
    		if(actor != null && (actor instanceof Map<?,?>)) {
    			if(actor.containsKey(YVelocity.KEY)) {
    				YVelocity yv = (YVelocity) actor.get(YVelocity.KEY);
    				yv.setData(speed);
    			}
    		}
    	};
    }
	
	
	@SuppressWarnings("unchecked")
	public static Consumer<Map<String, Component>> accelerateLeft (double speed) {
    	return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
    		if(actor != null && (actor instanceof Map<?,?>)) {
    			if(actor.containsKey(XAcceleration.KEY)) {
    				XAcceleration xa = (XAcceleration) actor.get(XAcceleration.KEY);
    				xa.setData(-speed);
    			}
    		}
    	};
    }
	
	@SuppressWarnings("unchecked")
	public static Consumer<Map<String, Component>> accelerateRight (double speed) {
    	return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
    		if(actor != null && (actor instanceof Map<?,?>)) {
    			if(actor.containsKey(XAcceleration.KEY)) {
    				XAcceleration xa = (XAcceleration) actor.get(XAcceleration.KEY);
    				xa.setData(speed);
    			}
    		}
    	};
    }
	
	@SuppressWarnings("unchecked")
	public static Consumer<Map<String, Component>> accelerateUp (double speed) {
    	return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
    		if(actor != null && (actor instanceof Map<?,?>)) {
    			if(actor.containsKey(YAcceleration.KEY)) {
    				YAcceleration ya = (YAcceleration) actor.get(YAcceleration.KEY);
    				ya.setData(-speed);
    			}
    		}
    	};
    }
	
	@SuppressWarnings("unchecked")
	public static Consumer<Map<String, Component>> accelerateDown (double speed) {
    	return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
    		if(actor != null && (actor instanceof Map<?,?>)) {
    			if(actor.containsKey(YAcceleration.KEY)) {
    				YAcceleration ya = (YAcceleration) actor.get(YAcceleration.KEY);
    				ya.setData(speed);
    			}
    		}
    	};
    }
	
	
	
	@SuppressWarnings("unchecked")
	public static Consumer<Map<String, Component>> addScore (double score) {
    	return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
    		if(actor != null && (actor instanceof Map<?,?>)) {
    			if(actor.containsKey(Score.KEY)) {
    				Score s = (Score) actor.get(Score.KEY);
    				s.setData(s.getData() + score);
    			}
    		}
    	};
    }
	
	
	
	
	@SuppressWarnings("unchecked")
	public static BiConsumer<Map<String, Component>,Map<String, Component>> transferScore() {
		return (Serializable & BiConsumer<Map<String, Component>,Map<String, Component>>) (actor1, actor2) -> {
    		if(actor1 != null && (actor1 instanceof Map<?,?>) && actor2 != null && (actor2 instanceof Map<?,?>)) {
    			if(actor1.containsKey(Player.KEY) && actor1.containsKey(Score.KEY) && actor2.containsKey(Score.KEY)) {
    				Score s1 = (Score) actor1.get(Score.KEY);
    				Score s2 = (Score) actor2.get(Score.KEY);
    				
    				s1.setData(s1.getData() + s2.getData());
    			}
    		}
    	};
	}
	
	
	@SuppressWarnings("unchecked")
	public static BiConsumer<Map<String, Component>,Map<String, Component>> xFriction(double stickiness) {
		return (Serializable & BiConsumer<Map<String, Component>,Map<String, Component>>) (actor1, actor2) -> {
    		if(actor1 != null && (actor1 instanceof Map<?,?>) && actor2 != null && (actor2 instanceof Map<?,?>)) {
    			if(actor1.containsKey(XVelocity.KEY) && actor1.containsKey(XAcceleration.KEY)) {
    				XVelocity xv = (XVelocity) actor1.get(XVelocity.KEY);
    				XAcceleration xa = (XAcceleration) actor1.get(XAcceleration.KEY);
    				
    				if(xv.getData() > 0) {
    					xa.setData(-stickiness);
    				} else if(xv.getData() < 0) {
    					xa.setData(stickiness);
    				}
    			}
    		}
    	};
	}
	
	
    
	
	
	
	
	
	
	
	
    /**
     * This would be an AI component that has an enemy follow you
     * @param followed Player/entity being followed
     * @param tracker  Enemy/entity tracking the followed
     * @return action which result in the tracker moving towards the followed
     */
    public static Consumer followsYou (Entity followed, Entity tracker) {
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
    public static Consumer patrol(Map<String, Component> actor, List<Point> coordinates) {
        Velocity v = (Velocity) actor.get(Velocity.KEY);
        Position p = (Position) actor.get(Position.KEY);

        AtomicReference<Point> destination = new AtomicReference<>(coordinates.get(0));
        AtomicInteger current = new AtomicInteger();

        return (Serializable & Consumer) (time) -> {
            v.setXVel((destination.get().getX()-p.getXPos())/
					(distance(p.getXPos(), p.getYPos(), destination.get().getX(), destination.get().getY()) * 100));
            v.setYVel((destination.get().getY()-p.getYPos())/
					(distance(p.getXPos(), p.getYPos(), destination.get().getX(), destination.get().getY()) * 100));
            if ((distance(p.getXPos(), p.getYPos(), destination.get().getX(), destination.get().getY()) * 100) < 10) {
                if (current.get() == coordinates.size() - 1) current.set(0);
                else current.getAndIncrement();
                destination.set(coordinates.get(current.get()));
            }
        };
    }


    private static double distance (double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(y1-y2, 2) + Math.pow(x1 - x2, 2)); //distance between two positions/points
    }

}