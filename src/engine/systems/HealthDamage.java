package engine.systems;
<<<<<<< HEAD
/**
 * A system that handles what happens when two entities collide, one having health component and the other damage
 * @author Stefani Vukajlovic
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;
=======


import java.util.*;

>>>>>>> 1b29115742bd5604061c98ff89ceb50bf8ce3c64

import engine.components.Component;
import engine.components.Damage;
import engine.components.Health;
public class HealthDamage implements ISystem {
	public static int HEALTH_INDEX = 0;
	public static int DAMAGE_INDEX = 1;
	
	private Map<Integer, List<Component>> healthComponents;
	private Set<Integer> activeComponents;

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
		if(healthComponents.containsKey(pid)) {
    		healthComponents.remove(pid);
    	}  
	}

	public void setActives(Set<Integer> actives) {
		activeComponents = actives;
	}

	public void execute(double time) {
		activeComponents.forEach((key) -> {
			if (healthComponents.containsKey(key)) {
				List<Component> list = healthComponents.get(key);
				Health h = (Health) list.get(HEALTH_INDEX);
				Damage d = (Damage) list.get(DAMAGE_INDEX);

				h.setHealth(h.getHealth() - d.getDamage());
				d.decrementLife();
				if(d.getLifetime() == 0) {
					healthComponents.remove(h.getParentID());
				}
			}
		});
	}

	
}
