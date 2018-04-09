package engine.systems;


import java.util.*;

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
    private Set<Integer> activeComponents;

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
    
    @Override
    public void setActives(Set<Integer> actives) {
        activeComponents = actives;
    }
    /**
     * Apply changes in velocities to positions
     */
    public void execute(double time) {
        for (int pid : activeComponents) {
            if (handledComponents.containsKey(pid)) {
                List<Component> components = handledComponents.get(pid);

                Velocity v = (Velocity) components.get(VELOCITY_INDEX);
                Position p = (Position) components.get(POSITION_INDEX);

                p.setXPos(p.getXPos() + v.getXVel()*time);
                p.setYPos(p.getYPos() + v.getYVel()*time);
            }
        }
    }

}
