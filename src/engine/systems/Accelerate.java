package engine.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private static final int ACCELERATION_INDEX = 0;
	private static final int VELOCITY_INDEX = 1;

	private Map<Integer, List<Component>> handledComponents = new HashMap<>();
    
	/**
	 * Adds acceleration and velocity components from <String, Component> Map
	 * 
	 * @param pid	Parent ID of components
	 * @param components	Map of components for given parent
	 */
    public void addComponent(int pid, Map<String, Component> components) {
    	if(!handledComponents.containsKey(pid)) {
    		if (components.containsKey("Acceleration") && components.containsKey("Velocity")) {
    			List<Component> newComponents = new ArrayList<>();
    			newComponents.add(components.get("Acceleration"));
    			newComponents.add(components.get("Velocity"));
    			handledComponents.put(pid, newComponents);
			}
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

    /**
     * Updates velocity values based on Acceleration component
     * 
     *  @param time	Update time for game loop
     */
	public void execute(double time) {
		for (int pid : handledComponents.keySet()) {
			List<Component> activeComponents = handledComponents.get(pid);

			Acceleration a = (Acceleration) activeComponents.get(ACCELERATION_INDEX);
			Velocity v = (Velocity) activeComponents.get(VELOCITY_INDEX);

			v.setXVel(v.getXVel() + a.getxAcc()*time);
			v.setYVel(v.getYVel() + a.getyAcc()*time);
		}
	}

}

