package engine.setup;

import java.util.Map;
import engine.components.Component;

public class EntityManager {
	private static Map<Integer, Map<String, Component>> entities;
	private SystemManager SM;
	
    public EntityManager (Map<Integer, Map<String, Component>> entities, SystemManager SM) {
    		this.SM = SM;
        this.entities = entities;
    }
    
    public static boolean hasComponent(int pid, String component) {
    		Map<String,Component> components = entities.get(pid);
    		return components.containsKey(component);
    }
    
    public static Map<Integer, Map<String, Component>> getEntities(){
    		return entities;
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
