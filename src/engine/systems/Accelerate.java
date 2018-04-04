package engine.systems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.components.Acceleration;
import engine.components.Component;
import engine.components.Position;
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

	/**
	 * Apply changes in velocities to positions
	 * @param velocities updated velocity components
	 */
	public void execute(List<Velocity> velocities) {
		for(Velocity vel:velocities) {
			int pid = vel.getParentID();
			//just for debug, delete later
			if(!handledComponents.containsKey(pid)) {
				System.out.println("Errors: Acceleration system has missing components!");
				return;
			}

			handledComponents.put(pid,vel);
		}
	}

	@Override
	public void execute(double elapsedTime) {
		velocities.keySet().forEach((pid) -> {
			if(accelerations.containsKey(pid)) {
				Velocity v = velocities.get(pid);
				Acceleration a = accelerations.get(pid);
			}
		});
	}

}

