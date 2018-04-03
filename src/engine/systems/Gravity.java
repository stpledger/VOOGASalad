package engine.systems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.components.Velocity;

/**
 * System to apply gravity to an object based on Velocity component's gravity acceleration
 * Required component: Velocity
 * 
 * @author fitzj
 * @author Yameng
 */
public class Gravity{
	private Map<Integer, Velocity> handledComponents;
    private Time time;
    
    public Gravity() {
    		handledComponents = new HashMap<>();
    }
    
    /**
     * Adds velocity component to system map
     * @param ic	Velocity component to be added
     */
    public void addComponent(int pid, Velocity ic) {
	    	if(!handledComponents.containsKey(pid)) {
	    		handledComponents.put(pid, ic);
	    	}
    }
    
    /**
     * Removes velocity component from system map
     * @param ic	Velocity component to be removed
     */
    public void removeComponent(int pid) {
	    	if(handledComponents.containsKey(pid)) {
	    		handledComponents.remove(pid);
	    	}
    }

    /**
	 * Apply gravity effect on given objects
	 * @param ids ID of given objects to apply gravity effects on
	 */
    public void execute(List<Integer> ids) {
	    	for(int id: ids) {
	    		//just for debug, delete later
	    		if(!handledComponents.containsKey(id)) {
	    			System.out.println("Errors: Gravity system has missing components!");
	    			return;
	    		}
	    		
	    		Velocity vel = handledComponents.get(id);
	    		vel.setYVel(vel.getYVel() + vel.getGravAcc() * time);
	    		handledComponents.put(id, vel);
	    	}
    }
    
}
