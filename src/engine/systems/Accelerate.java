package engine.systems;

import java.util.*;

import engine.components.Acceleration;
import engine.components.Component;
import engine.components.Velocity;

/**
 * **
 * System to apply changes in velocities
 * Required component: Velocity
 *
 * @author Yameng
 */

public class Accelerate implements ISystem {
	private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();
	private Set<Integer> activeComponents;
    
	/**
	 * Adds acceleration and velocity components from <String, Component> Map
	 * 
	 * @param pid	Parent ID of components
	 * @param components	Map of components for given parent
	 */
    public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(Acceleration.getKey()) && components.containsKey(Velocity.getKey())) {
			Map<String, Component> newComponents = new HashMap<>();
			newComponents.put(Acceleration.getKey(),components.get(Acceleration.getKey()));
			newComponents.put(Velocity.getKey(),components.get(Velocity.getKey()));
			handledComponents.put(pid, newComponents);
		}
    	
    }
    
    /**
     * Removes components for given ID
     * 
     * @param pid	Parent whos components will be removed
     */
    public void removeComponent(int pid) {
	    	if(handledComponents.containsKey(pid)) {
	    		handledComponents.remove(pid);
	    	}
    }

	@Override
	public void setActives(Set<Integer> actives) {
	    	activeComponents = actives;
	    	activeComponents.retainAll(handledComponents.keySet());
	}

	/**
     * Updates velocity values based on Acceleration component
     * 
     *  @param time	Update time for game loop
     */
	public void execute(double time) {
		for (int pid : activeComponents) {
			Map<String,Component> activeComponents = handledComponents.get(pid);

			Acceleration a = (Acceleration) activeComponents.get(Acceleration.getKey());
			Velocity v = (Velocity) activeComponents.get(Velocity.getKey());

			v.setXVel(v.getXVel() + a.getxAcc()*time);
			v.setYVel(v.getYVel() + a.getyAcc()*time);
		}
	}

}

