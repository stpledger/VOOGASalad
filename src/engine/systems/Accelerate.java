package engine.systems;

import java.util.HashMap;
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

public class Accelerate implements System {
	
	private Map<Integer, Velocity> velocities;
	private Map<Integer, Acceleration> accelerations;

	public Accelerate() {
		velocities = new HashMap<>();
		accelerations = new HashMap<>();
	}
	
	
	/**
	 * Adds velocity components to system map
	 * @param pid parent ID of component to be removed
	 * @param vel velocity component to be removed
	 */
	public void addComponent(int pid, Component c) {
		if(c instanceof Velocity) {
			velocities.put(pid, (Velocity) c);
		} else if(c instanceof Acceleration) {
			accelerations.put(pid, (Acceleration) c);
		}
	}

	/**
	 * Removes position and velocity component from system map
	 * @param pid parent ID of Velocity component to be removed
	 */
	public void removeComponent(int pid) {
		if(velocities.containsKey(pid)) {
			velocities.remove(pid);
		}
		if(accelerations.containsKey(pid)) {
			accelerations.remove(pid);
		}
	}

	public void execute(double elapsedTime) {
		velocities.keySet().forEach((pid) -> {
			if(accelerations.containsKey(pid)) {
				Velocity v = velocities.get(pid);
				Acceleration a = accelerations.get(pid);
				
				v.setXVel(v.getXVel() + elapsedTime * a.getxAcc());
				v.setYVel(v.getYVel() + elapsedTime * a.getyAcc());
			}
		});
	}

}

