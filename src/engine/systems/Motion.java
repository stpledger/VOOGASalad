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
	//private static final int VELOCITY_INDEX = 0;
    //private static final int POSITION_INDEX = 1;

    private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();
    private Set<Integer> activeComponents;


        public void addComponent(int pid, Map<String, Component> components) {
            if (components.containsKey("Velocity") && components.containsKey("Position")) {
                Map<String, Component> newComponents = new HashMap<>();
                newComponents.put(Velocity.getKey(),components.get(Velocity.getKey()));
                newComponents.put(Position.getKey(),components.get(Position.getKey()));
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
        activeComponents.retainAll(handledComponents.keySet());
    }

    /**
     * Apply changes in velocities to positions
     */
    public void execute(double time) {
        for (int pid : activeComponents) {
            Map<String, Component> components = handledComponents.get(pid);

            Velocity v = (Velocity) components.get(Velocity.getKey());
            Position p = (Position) components.get(Position.getKey());

            p.setXPos(p.getXPos() + v.getXVel()*time);
            p.setYPos(p.getYPos() + v.getYVel()*time);
        }
    }

}
