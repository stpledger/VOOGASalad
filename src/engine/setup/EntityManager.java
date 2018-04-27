package engine.setup;

import java.util.Map;
import engine.components.Component;
import engine.components.groups.Damage;

public class EntityManager {
	private Map<Integer, Map<String, Component>> entities;
	private SystemManager sm;
	private RenderManager rm;
	
    public EntityManager(Map<Integer, Map<String, Component>> entities, RenderManager rm, SystemManager sm) {
    		this.entities = entities;
    		this.rm = rm;
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
    		if(map.containsKey(componentName) && !componentName.equals(Damage.KEY)) {
    			System.out.println("Try Adding duplicate " + componentName + " component in EntityManager!");
    			return;
    		}
    		
    		map.put(componentName,component);
    		sm.addComponent(pid,map);
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
		sm.removeEntity(pid);
    }
    
    public Component getComponent(int pid, String componentName) {
    		return entities.get(pid).get(componentName);
    }

    public void removeEntity (int pid) {
    	entities.remove(pid);
    	sm.removeEntity(pid);
    	sm.setActives(rm.render());
	}
}
