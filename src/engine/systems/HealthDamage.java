package engine.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.components.Component;
import engine.components.Damage;
import engine.components.Health;



public class HealthDamage implements ISystem {
	public static int HEALTH_INDEX = 0;
	public static int DAMAGE_INDEX = 1;
	
	private Map<Integer, List<Component>> healthComponents;

	public HealthDamage() {
		healthComponents = new HashMap<>();
	}
	public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(Health.getKey()) && components.containsKey(Damage.getKey())) {
			List<Component> newComponents = new ArrayList<>();
			newComponents.add(components.get(Health.getKey()));
			newComponents.add(components.get(Damage.getKey()));
			
			healthComponents.put(pid, newComponents);
		}
		
	}
	
    public void removeComponent(int pid) {
		if(handledComponents.containsKey(pid)) {
    		handledComponents.remove(pid);
    	}  
	}
	
	public void execute(double time) {
		healthComponents.forEach((key, list) -> {
			Health h = (Health) list.get(HEALTH_INDEX);
			Damage d = (Damage) list.get(DAMAGE_INDEX);
			
			h.setHealth(h.getHealth() - d.getDamage());
			d.decrementLife();
			if(d.getLifetime() == 0) {
				healthComponents.remove(h.getParentID());
			}
		});
	}

	
}
