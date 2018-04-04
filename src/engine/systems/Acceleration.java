package engine.systems;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

public class Acceleration{
	private Map<Integer, Velocity> handledComponents;

	/**
	 * Adds velocity components to system map
	 * @param pid parent ID of component to be removed
	 * @param vel velocity component to be removed
	 */
	public void addComponent(int pid, Velocity vel) {
		if(!handledComponents.containsKey(pid)) {
			handledComponents.put(pid, vel);
		}
	}

	/**
	 * Removes position and velocity component from system map
	 * @param pid parent ID of Velocity component to be removed
	 */

	public void removeComponent(int pid) {
		if(!handledComponents.containsKey(pid)) {
			handledComponents.remove(pid);
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

}

