package engine.systems;


import java.util.*;

import engine.components.Component;
import engine.components.XPosition;
import engine.components.XVelocity;
import engine.components.YPosition;
import engine.components.YVelocity;


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
        if (components.containsKey(XVelocity.KEY) && 
        	components.containsKey(YVelocity.KEY) && 
        	components.containsKey(XPosition.KEY) &&
        	components.containsKey(YPosition.KEY)) {
                Map<String, Component> newComponents = new HashMap<>();
                newComponents.put(XVelocity.KEY,components.get(XVelocity.KEY));
                newComponents.put(YVelocity.KEY,components.get(YVelocity.KEY));
                newComponents.put(XPosition.KEY,components.get(XPosition.KEY));
                newComponents.put(YPosition.KEY,components.get(YPosition.KEY));
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

            XVelocity vx = (XVelocity) components.get(XVelocity.KEY);
            YVelocity vy = (YVelocity) components.get(YVelocity.KEY);
            XPosition px = (XPosition) components.get(XPosition.KEY);
            YPosition py = (YPosition) components.get(YPosition.KEY);
            px.setData(px.getData() + vx.getData()*time);
            py.setData(py.getData() + vy.getData()*time);
        }
    }


}
