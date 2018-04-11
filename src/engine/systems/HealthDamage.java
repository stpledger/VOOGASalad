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

import engine.components.Acceleration;
import engine.components.Component;
import engine.components.Damage;
import engine.components.DamageLauncher;
import engine.components.Health;
import engine.components.Velocity;
import engine.setup.EntityManager;
public class HealthDamage implements ISystem {
	private Map<Integer, Map<String, Component>> handledComponents;
	private Set<Integer> activeComponents;

	public HealthDamage() {
		handledComponents = new HashMap<>();
	}

	public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(Health.getKey()) && components.containsKey(DamageLauncher.getKey())) {
			Map<String, Component> newComponents = new HashMap<>();
			newComponents.put(Health.getKey(),components.get(Health.getKey()));
			newComponents.put(DamageLauncher.getKey(),components.get(DamageLauncher.getKey()));
			handledComponents.put(pid, newComponents);
		}
		
	}
	
    public void removeComponent(int pid) {
		if(handledComponents.containsKey(pid)) {
    		handledComponents.remove(pid);
    	}  
	}

    public void addComponent(int pid, String componentName) {
		if(!componentName.equals(Health.getKey()) && !componentName.equals(Damage.getKey())) {
			return;
		}
		
		if(handledComponents.containsKey(pid)) {
			System.out.println("HealthDamage System tries adding duplicate " + componentName + " component for entity " + pid + " !");
		}
		

		Map<String, Component> map = new HashMap<>();
		map.put(componentName,EntityManager.getComponent(pid, componentName));
		if(componentName.equals(Health.getKey())) {
			Component component = EntityManager.getComponent(pid,Damage.getKey());
			if(component == null) {
				System.out.println("Entity " + pid + " has " + componentName + " component but has no " + Damage.getKey() + " component!");
				return;
			}
			map.put(Damage.getKey(), component);
		}
		else {
			Component component = EntityManager.getComponent(pid,Health.getKey());
			if(component == null) {
				System.out.println("Entity " + pid + " has " + componentName + " component but has no " + Health.getKey() + " component!");
				return;
			}
			map.put(Health.getKey(), component);
		}
		handledComponents.put(pid,map);
    }

	public void removeComponent(int pid, String componentName) {
		if(!componentName.equals(Health.getKey()) && !componentName.equals(Damage.getKey())) {
			return;
		}
		
		if(!handledComponents.containsKey(pid)) {
			System.out.println("HealthDamage System tries remove " + componentName + " from non-existing entity " + pid + " !");
		}
		
	
		handledComponents.remove(pid);
	}

	public void setActives(Set<Integer> actives) {
		activeComponents = actives;
		activeComponents.retainAll(handledComponents.keySet());
	}

	public void execute(double time) {
		activeComponents.forEach((key) -> {
			Map<String, Component> map = handledComponents.get(key);
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
