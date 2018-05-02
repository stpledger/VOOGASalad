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
public class Collidable implements Component, BehaviorComponent {

	public static final String KEY = "Collidable";
	
	private Map<CollisionDirection, BiConsumer<Map<String,Component>,Map<String,Component>>[]> actions;
	private Map<CollisionDirection, Consumer<Map<String, Component>>[]> singleActions;
	private int pid;
	
	public Collidable(int pid) {
		this.pid = pid;
		actions = new HashMap<>();
		singleActions = new HashMap<>();
	}
	
	/**
	 * Used within engine. Adds a consumer to the map by running a biconsumer with only its first argument.
	 * @param cd	Direction of collision
	 * @param c		Consumer defining action
	 */
	public void setOnDirection(CollisionDirection cd, Consumer<Map<String,Component>>... c) {
		singleActions.put(cd, c);
	}
	
	/**
	 * Used within engine. Adds a biconsumer to the map.
	 * @param cd	Collision direction
	 * @param c		Biconsumer to be added
	 */
	public void setOnDirection(CollisionDirection cd, BiConsumer<Map<String,Component>,Map<String,Component>>... c) {
		actions.put(cd, c);
	}
	
	/**
	 * Runs collision action for corresponding direction on given entities, typically the first entity is the one containing this component
	 * @param cd			Collision direction
	 * @param entityMap1	First entity, usually the one containing this component
	 * @param entityMap2	Second entity, usually the one being collided with
	 */
	public void action(CollisionDirection cd, Map<String, Component> entityMap1, Map<String, Component> entityMap2) {
		if(actions.containsKey(cd)) {
			for (BiConsumer<Map<String, Component>, Map<String, Component>> act : actions.get(cd) )
				act.accept(entityMap1, entityMap2);
		}
		if(singleActions.containsKey(cd)) {
			for (Consumer<Map<String, Component>> act : singleActions.get(cd)) {
				act.accept(entityMap1);
			}
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
	public void addBehavior(Object identifier, Consumer<Map<String,Component>> con) {
		if(identifier instanceof CollisionDirection) {
			this.setOnDirection((CollisionDirection) identifier, con);
		}
	}

	/**
	 * Adds a biconsumer, used by authoring
	 */
	@Override
	public void addBehavior(Object identifier, BiConsumer<Map<String,Component>,Map<String,Component>> bic) {
		if(identifier instanceof CollisionDirection) {
			this.setOnDirection((CollisionDirection) identifier, bic);
		}
	}

	@Override
	public int getPID() {
		return pid;
	}

}
