package engine.systems;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import engine.components.Acceleration;
import engine.components.Component;
import engine.components.Position;
import engine.components.Velocity;

/**
 * System to apply changes in positions to an object based on changes in velocities
 * Required component: Position, Velocity
 *
 * @author Yameng
 */

public class Motion implements System {
	private Map<Integer, Position> positions;
	private Map<Integer, Velocity> velocities;
    
	/**
     * Adds position and velocity components to system map
     * @param pid parent ID of component to be removed
     * @param pos position component to be removed vel velocity component to be removed
     */
    public void addComponent(int pid, Component c) {
    	if(c instanceof Velocity) {
			velocities.put(pid, (Velocity) c);
		} else if(c instanceof Position) {
			positions.put(pid, (Position) c);
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
    	if(positions.containsKey(pid)) {
    		positions.remove(pid);
    	}
    }

    /**
     * Apply changes in velocities to positions
     * @param velocities updated velocity components
     */
	public void execute(List<Component> velocities) {
		for(Component comp:velocities) {
			Velocity vel = (Velocity)comp;
			int pid = vel.getParentID();
			//just for debug, delete later
			if(!handledComponents.containsKey(pid)) {
				System.out.println("Errors: Motion system has missing components!");
				return;
			}
			
			List<Component> val = handledComponents.get(pid);
			Position pos = (Position)val.get(0);
			pos.setXPos(pos.getXPos() + vel.getXVel());
			pos.setYPos(pos.getYPos() + vel.getYVel());
			val.set(0, (Component)pos);
			handledComponents.put(pid,val);
		}
	}

	public void execute(double elapsedTime) {
		velocities.keySet().forEach((pid) -> {
			if(positions.containsKey(pid)) {
				Velocity v = velocities.get(pid);
				Position p = positions.get(pid);
				
				p.setxPos(p.getxPos() + elapsedTime * v.getXVel());
				p.setyPos(p.getyPos() + elapsedTime * v.getYVel());
			}
		});
	}

}
