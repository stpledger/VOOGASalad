package engine.actions;

import authoring.entities.Entity;
import engine.components.Health;
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
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import engine.components.DamageValue;
import engine.components.DamageLifetime;
import engine.components.Sprite;
import engine.setup.SystemManager;

/**
 * This is the actions class which contains many methods that return consumers representing actions in the
 * form of lambdas to be used by the entities in the game
 *
 * @author cndracos
 */
public class Actions {
    private SystemManager SM;
    public Actions(SystemManager SM){
        this.SM = SM;
    }

    /**
     * @param actor Entity moving left
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
	 * @return two new entity mapsama
	 */
	public static Consumer<Map<String, Component>> lowGravity(){
		return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
			if (actor != null && actor.containsKey(YAcceleration.KEY)) {
				YAcceleration YAcc = (YAcceleration) actor.get(YAcceleration.KEY);
				YAcc.setData(YAcc.getData() - 1.0);
			}
		};
	}
    
	
    /**
     * @return two new entity mapsama
     */
    public static BiConsumer<Map<String, Component>, Map<String, Component>> damage(){
        return (Serializable & BiConsumer<Map<String, Component>,Map<String, Component>>) (actor1, actor2) -> {
            if(actor1 != null && actor1.containsKey(Health.KEY)){
                if(actor2 != null && actor2.containsKey(DamageLifetime.KEY) && actor2.containsKey(DamageValue.KEY)){
                    DamageLifetime damagelifetime2 = (DamageLifetime)actor2.get(DamageLifetime.KEY);
                    DamageValue damagevalue2 = (DamageValue)actor2.get(DamageValue.KEY);
                    if(actor1.containsKey(DamageLifetime.KEY)){
                        DamageValue damagevalue1 = (DamageValue)actor1.get(DamageValue.KEY);
                        damagevalue1.setData(damagevalue1.getData() + damagevalue2.getData());
                        actor1.put(DamageValue.KEY, damagevalue1);
                    }
                    else{
                        actor1.put(DamageLifetime.KEY, damagelifetime2);
                        actor1.put(DamageValue.KEY, damagevalue2);
                        SystemManager.addComponent(damagelifetime2.getPID(), damagelifetime2);
                        SystemManager.addComponent(damagevalue2.getPID(), damagevalue2);
                    }
                }
            }

            if(actor2 != null && actor2.containsKey(Health.KEY)){
                if(actor1 != null && actor1.containsKey(DamageLifetime.KEY) && actor1.containsKey(DamageValue.KEY)){
                    DamageLifetime damagelifetime1 = (DamageLifetime)actor1.get(DamageLifetime.KEY);
                    DamageValue damagevalue1 = (DamageValue)actor1.get(DamageValue.KEY);
                    if(actor2.containsKey(DamageLifetime.KEY)){
                        DamageValue damagevalue2 = (DamageValue)actor2.get(DamageValue.KEY);
                        damagevalue2.setData(damagevalue2.getData() + damagevalue1.getData());
                        actor2.put(DamageValue.KEY, damagevalue2);
                    }
                    else{
                        actor2.put(DamageLifetime.KEY, damagelifetime1);
                        actor2.put(DamageValue.KEY, damagevalue1);
                        SystemManager.addComponent(damagelifetime1.getPID(), damagelifetime1);
                        SystemManager.addComponent(damagelifetime1.getPID(), damagevalue1);
                    }
                }
            }
        };
    }
    
    private void giveDamage(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
		if (player.containsKey(DamageValue.KEY) &&
				player.containsKey(DamageLifetime.KEY) &&
				collider.containsKey(Health.KEY)) {

			DamageValue dlv = (DamageValue) player.get(DamageValue.KEY);
			DamageLifetime dll = (DamageLifetime) player.get(DamageLifetime.KEY);

			sm.addComponent(colliderID, new DamageValue(playerID, dlv.getData()));
			sm.addComponent(colliderID, new DamageLifetime(playerID, dll.getData()));
		}
	}

    /**
     * @return two new entity maps
     */
    public Consumer<Map<String, Component>> changeSprite(Sprite alternative){
        return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
            if(actor != null && actor.containsKey(Sprite.KEY)){
                actor.put(Sprite.KEY, alternative);
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
    public static Consumer patrol(Map<String, Component> actor, List<Position> coordinates) {
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
