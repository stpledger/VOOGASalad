package engine.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.components.Component;
import engine.components.Damage;
import engine.components.Health;


public class HealthDamage implements ISystem {
	
	private static final int HEALTH_INDEX = 0;
	private static final int DAMAGE_INDEX = 1;
	
	private Map<Integer, List<Component>> handledComponents = new HashMap<>();
	private List<Component> activeComponents;
	
	
	@Override
	public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey("Health") && components.containsKey("Damage")) {
			List<Component> newComponents = new ArrayList<>();
			newComponents.add(components.get("Health"));
			newComponents.add(components.get("Damage"));
			handledComponents.put(pid, newComponents);
		}
		
	}

	@Override
	public void removeComponent(int pid) {
		if(handledComponents.containsKey(pid)) {
    		handledComponents.remove(pid);
    	}  
	}

	@Override
	public void execute(double elapsedTime) {
		for (int pid : handledComponents.keySet()) {
			activeComponents = handledComponents.get(pid);

			Health h = (Health) activeComponents.get(HEALTH_INDEX);
			Damage d = (Damage) activeComponents.get(DAMAGE_INDEX);
     
			if(d.getLifetime()!=0) {
				h.setHealth(h.getHealth()-d.getDamage());
				d.decrementLife();
			}
			
		}
		
	}

	
}
