package engine.systems;
/**
 * A system that handles what happens when two entities collide, one having health component and the other damage
 * @author sv116
 */
import java.util.HashMap;
import java.util.Map;

import java.util.*;

import engine.components.Component;
import engine.components.DamageLifetime;
import engine.components.DamageValue;

import engine.components.Health;
import engine.components.Lives;
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
		if (components.containsKey(Health.KEY)) {
			Map<String, Component> newComponents = new HashMap<>();
			newComponents.put(Health.KEY,components.get(Health.KEY));
			if(components.containsKey(Lives.KEY)) {
				newComponents.put(Lives.KEY, components.get(Lives.KEY));
			}
			handledComponents.put(pid, newComponents);
			  
		}
		else if (handledComponents.containsKey(pid) && components.containsKey(DamageValue.KEY) && components.containsKey(DamageLifetime.KEY)) {
			Map<String, Component> newComponents = handledComponents.get(pid);
			DamageValue d = (DamageValue) components.get(DamageValue.KEY);
			DamageLifetime dl = (DamageLifetime) components.get(DamageLifetime.KEY);

			if (d.getPID()!=pid) {
				newComponents.put(DamageValue.KEY, d);
				newComponents.put(DamageLifetime.KEY, dl);
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
			if(map.containsKey(DamageValue.KEY) && map.containsKey(DamageLifetime.KEY) && map.containsKey(Health.KEY)) {
				Health h = (Health) map.get(Health.KEY);
				DamageValue d = (DamageValue) map.get(DamageValue.KEY);
				DamageLifetime dl = (DamageLifetime) map.get(DamageLifetime.KEY);

				if (h.getPID()!=d.getPID()) {
					h.setData(h.getData() - d.getData());
					dl.setData(dl.getData() - 1);
					if(d.getData() <= 0) {
						map.remove(d.getKey());
					}
				}

				if(h.getData() <= 0) {
					if(map.containsKey(Lives.KEY)) {
						System.out.println("Entity has lives");
					  Lives l =((Lives) map.get(Lives.KEY));
							l.setData(l.getData()-1);
							System.out.println(l.getData());
					   if(l.getData()>=0) {
						h.resetHealth();
					   }
					   else {
						   sm.removeEntity(key);
							System.out.println("removing e");
					   }
					}
					else {
					sm.removeEntity(key);
					System.out.println("removing");
					}
					//lives-health
				}
			}

		}

	}

}
