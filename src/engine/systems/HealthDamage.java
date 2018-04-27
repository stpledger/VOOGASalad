package engine.systems;
/**
 * A system that handles what happens when two entities collide, one having health component and the other damage
 * @author Stefani Vukajlovic
 */
import java.util.HashMap;
import java.util.Map;

import java.util.*;

import engine.components.Component;
import engine.components.Damage;
import engine.components.Health;

import engine.setup.SystemManager;

public class HealthDamage implements ISystem {
	private Map<Integer, Map<String, Component>> handledComponents;
	private Set<Integer> activeComponents;
	private SystemManager sm;
	
	public HealthDamage(SystemManager sm) {
		handledComponents = new HashMap<>();
		this.sm = sm;
	}

	public void addComponent(int pid, Map<String, Component> components) {
		Map<String, Component> newComponents = new HashMap<>();
		if (components.containsKey(Health.KEY)) {
			newComponents.put(Health.KEY,components.get(Health.KEY));
			handledComponents.put(pid, newComponents);
		}
		else if (components.containsKey(Damage.KEY) && handledComponents.containsKey(pid)) {
			Damage d = (Damage) components.get(Damage.KEY);
			if (d.getParentID()!=pid) {
				newComponents.put(Damage.KEY, d);
				handledComponents.put(pid, newComponents);
			}
		}
		
	}
	
    public void removeComponent(int pid) {
		if(handledComponents.containsKey(pid)) {
    		handledComponents.remove(pid);
    	}  
	}

	public void setActives(Set<Integer> actives) {
		Set<Integer> myActives = new HashSet<>(actives);
		myActives.retainAll(handledComponents.keySet());
		activeComponents = myActives;
	}

	public void execute(double time) {
		for (int key : activeComponents) {
			Map<String, Component> map = handledComponents.get(key);
			if(map.containsKey(Damage.KEY) && map.containsKey(Health.KEY)) {
				Health h = (Health) map.get(Health.KEY);
				Damage d = (Damage) map.get(Damage.KEY);

				if (h.getParentID()!=d.getParentID()) {
					h.setHealth(h.getHealth() - d.getDamage());
					d.decrementLife();
					if(d.getLifetime() == 0) {
						map.remove(d.getKey());
					}
				}

				if(h.getHealth() <= 0) {
					sm.removeEntity(key);
					System.out.println("removing");
				}

			}
		}
	}

}
