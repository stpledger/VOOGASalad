package engine.systems;
/**
 * A system that handles what happens when two entities collide, one having health component and the other damage
 * @author Stefani Vukajlovic
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;

import engine.components.Component;
import engine.components.Damage;
import engine.components.DamageLauncher;
import engine.components.Health;
import engine.setup.EntityManager;
public class HealthDamage implements ISystem {
	private Map<Integer, Map<String, Component>> healthComponents;
	private Set<Integer> activeComponents;

	public HealthDamage() {
		healthComponents = new HashMap<>();
	}

	public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(Health.getKey()) && components.containsKey(DamageLauncher.getKey())) {
			Map<String, Component> newComponents = new HashMap<>();
			newComponents.put(Health.getKey(),components.get(Health.getKey()));
			newComponents.put(DamageLauncher.getKey(),components.get(DamageLauncher.getKey()));
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
		activeComponents.retainAll(healthComponents.keySet());
	}

	public void execute(double time) {
		activeComponents.forEach((key) -> {
			Map<String, Component> map = healthComponents.get(key);
			Health h = (Health) map.get(Health.getKey());
			if(map.containsKey(Damage.getKey())) {
				Damage d = (Damage) map.get(Damage.getKey());

				if (h.getParentID()!=d.getParentID()) {
					h.setHealth(h.getHealth() - d.getDamage());
					d.decrementLife();
				}

				Component dComponent = (Component)d;
				map.put(Damage.getKey(), dComponent);
				if(d.getLifetime() == 0) {
					EntityManager.removeComponent(key, Damage.getKey(), dComponent);
				}

			}
			
			if(h.getHealth() <= 0) {
				//kill the object
			}
			
		});
	}

	
}
