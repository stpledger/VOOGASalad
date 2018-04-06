package engine.systems;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.components.Component;
import engine.components.Position;
import engine.components.Velocity;

/**
 * ISystem to apply changes in positions to an object based on changes in velocities
 * Required component: Position, Velocity
 *
 * @author Yameng
 */

public class Motion implements ISystem {

	private static final int VELOCITY_INDEX = 0;
	private static final int POSITION_INDEX = 1;

	private Map<Integer, List<Component>> handledComponents = new HashMap<>();
	private List<Component> activeComponents;
    
	
	
	/**
     * Adds position and velocity components to system map
     * @param pid parent ID of component to be removed
     * @param components position component to be removed vel velocity component to be removed
     */

    public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(Velocity.getKey()) && components.containsKey(Position.getKey())) {
			List<Component> newComponents = new ArrayList<>();
			newComponents.add(components.get(Velocity.getKey()));
			newComponents.add(components.get(Position.getKey()));
			handledComponents.put(pid, newComponents);
		}
    }
    
    /**
     * Removes position and velocity component from system map
     * @param pid parent ID of Velocity component to be removed
     */
    @Override
    public void removeComponent(int pid) {

    	if(handledComponents.containsKey(pid)) {
    		handledComponents.remove(pid);
    	}
    }

    /**
     * Apply changes in velocities to positions
     */
	public void execute(double time) {
		for (int pid : handledComponents.keySet()) {
			activeComponents = handledComponents.get(pid);

			Velocity v = (Velocity) activeComponents.get(VELOCITY_INDEX);
			Position p = (Position) activeComponents.get(POSITION_INDEX);

			p.setXPos(p.getXPos() + v.getXVel()*time);
			p.setYPos(p.getYPos() + v.getYVel()*time);
		}
	}

}
