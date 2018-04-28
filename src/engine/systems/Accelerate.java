package engine.systems;

import java.util.*;

import engine.components.Component;
import engine.components.XAcceleration;
import engine.components.XVelocity;
import engine.components.YAcceleration;
import engine.components.YVelocity;

/**
 * **
 * System to apply changes in velocities
 * Required component: Velocity
 *
 * @author Yameng
 */

public class Accelerate implements ISystem{
	private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();
	private Set<Integer> activeComponents;
    	
	/**
	 * Adds acceleration and velocity components from <String, Component> Map
	 * 
	 * @param pid	Parent ID of components
	 * @param components	Map of components for given parent
	 */
    public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(XAcceleration.KEY) && 
			components.containsKey(YAcceleration.KEY) &&
			components.containsKey(XVelocity.KEY) &&
			components.containsKey(YVelocity.KEY)) {
			
			Map<String, Component> newComponents = new HashMap<>();
			newComponents.put(XAcceleration.KEY,components.get(XAcceleration.KEY));
			newComponents.put(YAcceleration.KEY,components.get(YAcceleration.KEY));
			newComponents.put(XVelocity.KEY,components.get(XVelocity.KEY));
			newComponents.put(YVelocity.KEY,components.get(YVelocity.KEY));
			
			handledComponents.put(pid, newComponents);
		}
    	
    }
    
    /**
     * Removes components for given ID
     * 
     * @param pid	Parent whos components will be removed
     */
    public void removeComponent(int pid) {
	    	if(handledComponents.containsKey(pid)) {
	    		handledComponents.remove(pid);
	    	}
    }

    /*public void addComponent(int pid, String componentName) {
    	
    		if(!componentName.equals(Acceleration.KEY) && !componentName.equals(Velocity.KEY)) {
    			return;
    		}
    		
    		if(handledComponents.containsKey(pid)) {
    			System.out.println("Accelerate System tries adding duplicate " + componentName + " component for entity " + pid + " !");
    		}
    		

    		Map<String, Component> map = new HashMap<>();
    		map.put(componentName, em.getComponent(pid, componentName));
    		if(componentName.equals(Acceleration.KEY)) {
    			Component component = em.getComponent(pid,Velocity.KEY);
    			if(component == null) {
    				System.out.println("Entity " + pid + " has " + componentName + " component but has no " + Velocity.KEY + " component!");
    				return;
    			}
    			map.put(Velocity.KEY, component);
    		}
    		else {
    			Component component = em.getComponent(pid,Acceleration.KEY);
    			if(component == null) {
    				System.out.println("Entity " + pid + " has " + componentName + " component but has no " + Acceleration.KEY + " component!");
    				return;
    			}
    			map.put(Acceleration.KEY, component);
    		}
    		handledComponents.put(pid,map);
    }*/
    
    /*@Override
    public void removeComponent(int pid, String componentName) {
		if(!componentName.equals(Acceleration.KEY) && !componentName.equals(Velocity.KEY)) {
			return;
		}
		
		if(!handledComponents.containsKey(pid)) {
			System.out.println("Accelerate System tries remove " + componentName + " from non-existing entity " + pid + " !");
		}
		

		handledComponents.remove(pid);
    }*/

    
	@Override
	public void setActives(Set<Integer> actives) {
		Set<Integer> myActives = new HashSet<>(actives);
		myActives.retainAll(handledComponents.keySet());
		activeComponents = myActives;
	}

	/**
     * Updates velocity values based on Acceleration component
     * 
     *  @param time	Update time for game loop
     */
	public void execute(double time) {
		for (int pid : activeComponents) {
			Map<String,Component> activeComponents = handledComponents.get(pid);

			XAcceleration ax = (XAcceleration) activeComponents.get(XAcceleration.KEY);
			YAcceleration ay = (YAcceleration) activeComponents.get(YAcceleration.KEY);
			XVelocity vx = (XVelocity) activeComponents.get(XVelocity.KEY);
			YVelocity vy = (YVelocity) activeComponents.get(YVelocity.KEY);

			vx.setData(vx.getData() + ax.getData()*time);
			vy.setData(vy.getData() + ay.getData()*time);

		}
	}

	@Override
	public Map<Integer, Map<String, Component>> getHandledComponent() {
		return handledComponents;
	}

}

