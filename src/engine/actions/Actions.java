package engine.actions;

import authoring.entities.Entity;
import engine.components.*;

import java.awt.Point;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import engine.setup.SystemManager;

/**
 * This is the actions class which contains methods that represent any in-game actions done by an entity outside of
 * what is strictly defined by its component's values i.e. these are methods that act upon the components themselves,
 * given by the user. Each action is a consumer which accepts the entity's components such that it can act on them
 * as such, and in some actions that represent collisions, accept both the given entity's and the one it collides with's
 * components.
 *
 * @author cndracos
 * @author Yameng Liu
 */
public class Actions {
    private static SystemManager sm = null;

    public static void setSM(SystemManager sman) {
    	sm = sman;
    }

    /**

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
    				s2.setData(0);
    			}
    		}
    	};
	}
	
	
	@SuppressWarnings("unchecked")
	public static BiConsumer<Map<String, Component>,Map<String, Component>> xFriction(double stickiness) {
		return (Serializable & BiConsumer<Map<String, Component>,Map<String, Component>>) (actor1, actor2) -> {
    		if(actor1 != null && (actor1 instanceof Map<?,?>)) {
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


	@SuppressWarnings("unchecked")
	public static Consumer<Map<String, Component>> yGravity(double force){
		return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
			if (actor != null && actor.containsKey(YAcceleration.KEY)) {
				YAcceleration YAcc = (YAcceleration) actor.get(YAcceleration.KEY);
				YAcc.setData(force);
			}
		};
	}
    
	
    /**
	 *
	 * This is the main action which deals with components damaging one another. This method will be given
	 * to an entity, and defines ONLY HOW IT DAMAGES THE OTHER ENTITY, if that entity has health. In order for
	 * the original entity to be damaged by the other, the other must have this action as well, hence this
	 * represents a one-way transfer of damage.
	 *
     * @return Damage action
     */
    @SuppressWarnings("unchecked")
	public static BiConsumer<Map<String, Component>, Map<String, Component>> damage(){
        return (Serializable & BiConsumer<Map<String, Component>,Map<String, Component>>) (actor1, actor2) -> {
			if (actor1.containsKey(EntityType.KEY) && actor2.containsKey(EntityType.KEY)) {
				EntityType e1 = (EntityType) actor1.get(EntityType.KEY);
				EntityType e2 = (EntityType) actor1.get(EntityType.KEY);
				if (!e1.getData().equals(e2.getData())) { //cannot harm same entity type
					giveDamage(actor1, actor2);
				}
			}
        	else giveDamage(actor1, actor2);
		};
    }
    
    private static void giveDamage(Map<String, Component> player, Map<String, Component> collider) {
		if (player.containsKey(DamageValue.KEY) &&
				player.containsKey(DamageLifetime.KEY) &&
				collider.containsKey(Health.KEY)) {
			int playerID = player.get(DamageValue.KEY).getPID();
			int colliderID = collider.get(Health.KEY).getPID();

			DamageValue dlv = (DamageValue) player.get(DamageValue.KEY);
			DamageLifetime dll = (DamageLifetime) player.get(DamageLifetime.KEY);

			Map<String, Component> newDamageComponents = new HashMap<>();
			newDamageComponents.put(DamageValue.KEY, new DamageValue(playerID, dlv.getData()));
			newDamageComponents.put(DamageLifetime.KEY, new DamageLifetime(playerID, dll.getData()));
			if(sm != null) {
				sm.addEntity(colliderID, newDamageComponents); //passes new damage to the HealthDamage system
			}
		}
	}

    /**
     * @return two new entity maps
     */
    
    // Behavior already supported in sprite class
    
    /*@SuppressWarnings("unchecked")
	public Consumer<Map<String, Component>> changeSprite(Sprite alternative){
        return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
            if(actor != null && actor.containsKey(Sprite.KEY)){
                actor.put(Sprite.KEY, alternative);
            }
        };
    }*/

    /**
     * This would be an AI component that has an enemy follow you
     * @param followed Player/entity being followed
     * @return action which result in the tracker moving towards the followed
     */

    @SuppressWarnings("unchecked")
	public static Consumer<Map <String, Component>> followsYou (Entity followed, double speed) {
        XPosition px = (XPosition) followed.get(XPosition.KEY);
        YPosition py = (YPosition) followed.get(YPosition.KEY);

        return (Serializable & Consumer<Map <String, Component>>) (tracker) -> {
			XPosition tx = (XPosition) tracker.get(XPosition.KEY);
			YPosition ty = (YPosition) tracker.get(YPosition.KEY);
			XVelocity vx = (XVelocity) tracker.get(XVelocity.KEY);
			YVelocity vy = (YVelocity) tracker.get(YVelocity.KEY);

            vx.setData((px.getData() - tx.getData())* speed);
            vy.setData((py.getData() - ty.getData()) * speed);
        };
    }

    /**
     * A patrol action to be given to an AI component for enemies, blocks, etc. Goes to the given coordinates in
     * order then repeats.
     *
     * @param coordinates The positions the entity will visit
     * @return the actions which performs this method
     */

    @SuppressWarnings("unchecked")
	public static Consumer<Map <String, Component>> patrol(List<Point> coordinates, double speed) {
        AtomicReference<Point> destination = new AtomicReference<>(coordinates.get(0));
        AtomicInteger current = new AtomicInteger();

        return (Serializable & Consumer<Map<String, Component>>) (actor) -> {
			XVelocity xv = (XVelocity) actor.get(XVelocity.KEY);
			YVelocity yv = (YVelocity) actor.get(YVelocity.KEY);
			XPosition xp = (XPosition) actor.get(XPosition.KEY);
			YPosition yp = (YPosition) actor.get(YPosition.KEY);

            xv.setData((destination.get().getX()-xp.getData())/
					(distance(xp.getData(), yp.getData(), destination.get().getX(), destination.get().getY())) * speed);
            yv.setData((destination.get().getY()-yp.getData())/
					(distance(xp.getData(), yp.getData(), destination.get().getX(), destination.get().getY())) * speed);
            if ((distance(xp.getData(), yp.getData(), destination.get().getX(), destination.get().getY())) < 10) {
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
