package engine.systems;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import engine.components.Component;
import engine.components.Position;
import engine.components.Velocity;

/**
 * System to apply changes in positions to an object based on changes in velocities
 * Required component: Position, Velocity
 *
 * @author Yameng
 */

public class Motion implements ISystem{
	private Map<Integer, List<Component>> handledComponents;
    
	/**
     * Adds position and velocity components to system map
     * @param pid parent ID of Velocity component to be removed
     * @param pos position component to be removed vel velocity component to be removed
     */
    public void addComponent(int pid, Position pos, Velocity vel) {
	    	if(!handledComponents.containsKey(pid)) {
	    		List<Component> value = Arrays.asList(pos,vel);
	    		handledComponents.put(pid, value);
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
	@Override
	public void execute(List<Component> velocities) {
		for(Component comp:velocities) {
			Velocity vel = (Velocity)comp;
			int pid = vel.getParentID();
			if(!handledComponents.containsKey(pid)) {
				System.out.println("Motion system has missing components!");
			}
			
			List<Component> val = handledComponents.get(pid);
			Position pos = (Position)val.get(0);
			pos.setXPos(pos.getXPos() + vel.getXVel());
			pos.setYPos(pos.getYPos() + vel.getYVel());
			val.set(0, (Component)pos);
			handledComponents.put(pid,val);
		}
	}

}
