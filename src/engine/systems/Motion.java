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
	private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();
    private Set<Integer> activeComponents = new HashSet<>();


    public void addComponent(int pid, Map<String, Component> components) {
        if (components.containsKey(Velocity.KEY) && components.containsKey(Position.KEY)) {
                Map<String, Component> newComponents = new HashMap<>();
                newComponents.put(Velocity.KEY,components.get(Velocity.KEY));
                newComponents.put(Position.KEY,components.get(Position.KEY));
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
        Set<Integer> myActives = new HashSet<>(actives);
        myActives.retainAll(handledComponents.keySet());
        activeComponents = myActives;
    }

    /**
     * Apply changes in velocities to positions
     */
    public void execute(double time) {
        for (int pid : activeComponents) {
            Map<String, Component> components = handledComponents.get(pid);

            Velocity v = (Velocity) components.get(Velocity.KEY);
            Position p = (Position) components.get(Position.KEY);
            p.setXPos(p.getXPos() + v.getXVel()*time);
            p.setYPos(p.getYPos() + v.getYVel()*time);
        }
    }
	@Override
	public void addComponent(int pid, String componentName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeComponent(int pid, String componentName) {
		// TODO Auto-generated method stub
		
	}

    @Override
	public Map<Integer, Map<String, Component>> getHandledComponent() {
		return handledComponents;
	}
}
