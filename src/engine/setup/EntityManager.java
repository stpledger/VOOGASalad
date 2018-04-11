package engine.setup;

import java.util.Map;
import engine.components.Component;

public class EntityManager {
	private static Map<Integer, Map<String, Component>> entities;
	
    public EntityManager(Map<Integer, Map<String, Component>> entities) {
    		this.entities = entities;
	}

	public static boolean hasComponent(int pid, String component) {
    		Map<String,Component> components = entities.get(pid);
    		return components.containsKey(component);
    }
    
    public static Map<Integer, Map<String, Component>> getEntities(){
    		return entities;
    }
    
    public static void addComponent(int pid, String componentName, Component component) {
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
    		SystemManager.addComponent(pid,componentName);
    }
    
    public static void removeComponent(int pid, String componentName, Component component) {
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
		SystemManager.removeComponent(pid,componentName);
    }
    
    public static Component getComponent(int pid, String componentName) {
    		return entities.get(pid).get(componentName);
    }
    
}
