package engine.setup;

import java.util.Map;
import engine.components.Component;

public class EntityManager {
	private Map<Integer, Map<String, Component>> entities;
	private SystemManager sm;
	
    public EntityManager(Map<Integer, Map<String, Component>> entities, SystemManager sm) {
    		this.entities = entities;
    		this.sm = sm;
	}
    
    public void setSM(SystemManager sm) {
    	this.sm = sm;
    }

	public boolean hasComponent(int pid, String component) {
    		Map<String,Component> components = entities.get(pid);
    		return components.containsKey(component);
    }
    
    public Map<Integer, Map<String, Component>> getEntities(){
    		return entities;
    }
    
    public void addComponent(int pid, String componentName, Component component) {
    		if(!entities.containsKey(pid)) {
    			System.out.println("Missing entity in EntityManager!");
    			return;
    		}
    		
    		Map<String, Component> map = entities.get(pid);
    		if(map.containsKey(componentName)) {
    			System.out.println("Try Adding duplicate " + componentName + " component in EntityManager!");
    			return;
    		}
    		
    		map.put(componentName,component);
    		sm.addComponent(pid,componentName);
    }
    
    public void removeComponent(int pid, String componentName, Component component) {
    		if(!entities.containsKey(pid)) {
			System.out.println("Missing entity in EntityManager!");
			return;
		}
		
		Map<String, Component> map = entities.get(pid);
		if(!map.containsKey(componentName)) {
			System.out.println("Try removing non-existing " + componentName + " component in EntityManager!");
			return;
		}
		
		map.remove(componentName);
		sm.removeComponent(pid,componentName);
    }
    
    public Component getComponent(int pid, String componentName) {
    		return entities.get(pid).get(componentName);
    }
    
}
