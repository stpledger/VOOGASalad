package engine.components;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import engine.systems.collisions.CollisionDirection;

public class Collidable extends Conditional {

	public static final String KEY = "Collidable";
	
	private Map<CollisionDirection, Consumer<Map<String,Component>>> actions;
	
	public Collidable(int pid) {
		super(pid);
		actions = new HashMap<>();
	}
	
	public void setOnDirection(CollisionDirection cd, Consumer<Map<String,Component>> c) {
		actions.put(cd, c);
		setUpConditional();
	}
	
	@SuppressWarnings("unchecked")
	private void setUpConditional() {
		this.setAction((map, cd) -> {
			if(map == null || !(map instanceof Map<?,?>)) return;
			if(cd == null || !(cd instanceof CollisionDirection)) return;
			Map<String, Component> entity = (Map<String, Component>) map;
			CollisionDirection c = (CollisionDirection) cd;
			actions.get(c).accept(entity);
		});
	}

	@Override
	public String getKey() {
		return KEY;
	}

}
