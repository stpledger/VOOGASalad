package engine.systems;

import java.util.*;

import engine.components.Acceleration;
import engine.components.Component;
import engine.components.Velocity;
import engine.setup.EntityManager;

/**
 * **
 * System to apply changes in velocities
 * Required component: Velocity
 *
 * @author Yameng
 */

public class Accelerate implements ISystem{
	private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();
	private Set<Integer> activeComponents;
	
	/**
	 * Adds acceleration and velocity components from <String, Component> Map
	 * 
	 * @param pid	Parent ID of components
	 * @param components	Map of components for given parent
	 */
    public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(Acceleration.KEY) && components.containsKey(Velocity.KEY)) {
			Map<String, Component> newComponents = new HashMap<>();
			newComponents.put(Acceleration.KEY,components.get(Acceleration.KEY));
			newComponents.put(Velocity.KEY,components.get(Velocity.KEY));
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
		Set<Integer> myActives = new HashSet<>(actives);
		myActives.retainAll(handledComponents.keySet());
		activeComponents = myActives;
	}

	/**
     * Updates velocity values based on Acceleration component
     * 
     *  @param time	Update time for game loop
     */
	public void execute(double time) {
		for (int pid : activeComponents) {
			Map<String,Component> activeComponents = handledComponents.get(pid);

			Acceleration a = (Acceleration) activeComponents.get(Acceleration.KEY);
			Velocity v = (Velocity) activeComponents.get(Velocity.KEY);

			v.setXVel(v.getXVel() + a.getxAcc()*time);
			v.setYVel(v.getYVel() + a.getyAcc()*time);
		}
	}


}

