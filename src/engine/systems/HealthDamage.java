package engine.systems;

import java.util.*;

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

	@Override
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

	@Override
	public Map<Integer, List<Component>> getAllComponents(){
		return handledComponents;
	}
	
}
