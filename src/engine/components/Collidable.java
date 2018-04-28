package engine.components;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import engine.systems.collisions.CollisionDirection;

public class Collidable extends Conditional implements BehaviorComponent {

	public static final String KEY = "Collidable";
	
	private Map<CollisionDirection, BiConsumer<Map<String,Component>,Map<String,Component>>> actions;
	
	public Collidable(int pid) {
		super(pid);
		actions = new HashMap<>();
	}
	
	public void setOnDirection(CollisionDirection cd, Consumer<Map<String,Component>> c) {
		actions.put(cd, (entity1, entity2) -> {
			c.accept(entity1);
		});
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

	@Override
	public void addBehavior(Object identifier, Consumer con) {
		if(identifier instanceof CollisionDirection) {
			this.setOnDirection((CollisionDirection) identifier, con);
		}
	}

	@Override
	public void addBehavior(Object identifier, BiConsumer bic) {
		if(identifier instanceof CollisionDirection) {
			this.setOnDirection((CollisionDirection) identifier, bic);
		}
	}

}
