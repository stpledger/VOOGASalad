package engine.setup;

import java.util.Map;
import engine.components.Component;

public class EntityManager {
	private Map<Integer, Map<String, Component>> entities;
	private SystemManager SM;
	
    public EntityManager (Map<Integer, Map<String, Component>> entities, SystemManager SM) {
    		this.SM = SM;
        this.entities = entities;
    }
    
    /**
     * For next step. Not implemented now.
    public void addComponent(int pid, String componentName) {
    		Map<String, Component> components = entities.get(pid);
    		
    		if(!components.containsKey(componentName)) {
    			components.add(componentName,);
    		}
    		
        SM.addComponent(pid,components);
    }
	
    
    public void removeComponent(int pid, String componentName) {
	    	Map<String, Component> components = entities.get(pid);
			
		if(components.containsKey(componentName)) {
			components.remove(componentName);
		}
			
	    SM.removeComponent(pid,components);
    }
	**/
}
