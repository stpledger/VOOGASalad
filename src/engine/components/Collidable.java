package engine.components;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import engine.systems.collisions.CollisionDirection;

/**
 * Component that defines collision actions to be executed on a collision. Uses a biconsumer to define
 * collision action so that both the colliding and collided entity can be altered.
 * @author fitzj
 *
 */
public class Collidable extends Conditional implements BehaviorComponent {

	public static final String KEY = "Collidable";
	
	private Map<CollisionDirection, BiConsumer<Map<String,Component>,Map<String,Component>>> actions;
	
	public Collidable(int pid) {
		super(pid);
		actions = new HashMap<>();
	}
	
	/**
	 * Used within engine. Adds a consumer to the map by running a biconsumer with only its first argument.
	 * @param cd	Direction of collision
	 * @param c		Consumer defining action
	 */
	public void setOnDirection(CollisionDirection cd, Consumer<Map<String,Component>> c) {
		actions.put(cd, (entity1, entity2) -> {
			c.accept(entity1);
		});
	}
	
	/**
	 * Used within engine. Adds a biconsumer to the map.
	 * @param cd	Collision direction
	 * @param c		Biconsumer to be added
	 */
	public void setOnDirection(CollisionDirection cd, BiConsumer<Map<String,Component>,Map<String,Component>> c) {
		actions.put(cd, c);
	}
	
	/**
	 * Runs collision action for corresponding direction on given entities, typically the first entity is the one containing this component
	 * @param cd			Collision direction
	 * @param entityMap1	First entity, usually the one containing this component
	 * @param entityMap2	Second entity, usually the one being collided with
	 */
	@SuppressWarnings("unchecked")
	public void action(CollisionDirection cd, Map<String, Component> entityMap1, Map<String, Component> entityMap2) {
		if(actions.containsKey(cd)) {
			this.setAction((entity, entity2) -> {
				if(entity instanceof Map<?,?> && entity2 instanceof Map<?,?>) {
    				actions.get(cd).accept((Map<String, Component>) entity, (Map<String, Component>) entity2);
    			}
			});
			this.setCondition(() -> {
				return entityMap1;
			});
			this.evaluate(entityMap2);
		}
	}

	@Override
	public String getKey() {
		return KEY;
	}

	/**
	 * Adds a consumer, used by authoring
	 */
	@Override
	public void addBehavior(Object identifier, Consumer con) {
		if(identifier instanceof CollisionDirection) {
			this.setOnDirection((CollisionDirection) identifier, con);
		}
	}

	/**
	 * Adds a biconsumer, used by authoring
	 */
	@Override
	public void addBehavior(Object identifier, BiConsumer bic) {
		if(identifier instanceof CollisionDirection) {
			this.setOnDirection((CollisionDirection) identifier, bic);
		}
	}

}
