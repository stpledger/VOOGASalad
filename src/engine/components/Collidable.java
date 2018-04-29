package engine.components;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import engine.systems.collisions.CollisionDirection;

public class Collidable extends Conditional {

	public static final String KEY = "Collidable";
	
	private Map<CollisionDirection, BiConsumer<Map<String,Component>,Map<String,Component>>> actions;
	
	public Collidable(int pid) {
		super(pid);
		actions = new HashMap<>();
	}
	
	public void setOnDirection(CollisionDirection cd, BiConsumer<Map<String,Component>,Map<String,Component>> c) {
		actions.put(cd, c);
	}
	
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

}
