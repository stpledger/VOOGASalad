package engine.systems;

import java.util.HashMap;
import java.util.Map;

import engine.components.Component;
import engine.components.Health;
import engine.components.Poison;



public class HealthDamage implements ISystem {
	
	
	private Map<Integer, Health> healthComponents;

	public HealthDamage() {
		healthComponents = new HashMap<>();
	}
	
	@Override
	public void addComponent(int pid, Component h) {
		if(h instanceof Health) {
			healthComponents.put(pid, (Health) h);
		}
	}
	
	@Override
	public void removeComponent(int pid) {
		if(healthComponents.containsKey(pid)) {
    		healthComponents.remove(pid);
    	}  
	}
	
	public void execute(int pid, Poison p) {
		if(healthComponents.containsKey(pid)) {
			double change = healthComponents.get(pid).getHealth()-p.getPoison();
		    healthComponents.get(pid).setHealth(change);
		}
	}
}
