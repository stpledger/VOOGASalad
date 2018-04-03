package engine.systems;

import java.util.HashMap;
import java.util.Map;

import engine.components.Velocity;

/**
 * System to apply gravity to an object based on Velocity component's gravity acceleration
 * Required component: Velocity
 * 
 * @author fitzj
 */
public class Gravity implements ISystem {
	
	private Map<Integer, Velocity> velocityComponents;
    
    public Gravity() {
    	velocityComponents = new HashMap<>();
    }
    
    @Override
    public void execute(double time) {
    	velocityComponents.forEach((pid, vel) -> {
    		vel.setYVel(vel.getYVel() + vel.getGravAcc() * time);
    	});
    }
    
    /**
     * Adds velocity component to system map
     * @param ic	Velocity component to be added
     */
    public void addComponent(int pid, Velocity ic) {
    	if(!velocityComponents.containsKey(pid)) {
    		velocityComponents.put(pid, ic);
    	}
    }
    
    /**
     * Removes velocity component from system map
     * @param ic	Velocity component to be removed
     */
    public void removeComponent(int pid) {
    	if(velocityComponents.containsKey(pid)) {
    		velocityComponents.remove(pid);
    	}
    }

}
