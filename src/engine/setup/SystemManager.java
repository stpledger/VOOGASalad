package engine.setup;

import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.systems.ISystem;
import engine.systems.collisions.Collision;
import engine.components.Component;

public class SystemManager {

    private static List<ISystem> systems;
    private static Collision collision;
    
    public SystemManager (List<ISystem> systems, Collision collision) {
        this.systems = systems;
        this.collision = collision;
    }

    public void addSystem(ISystem system) {
    		systems.add(system);
    }
    
    public static void addEntity(int pid, Map<String, Component> components) {
        for (ISystem s : systems) {
            s.addComponent(pid, components);
        }
    }

    public static void removeEntity (int pid) {
        for (ISystem s : systems) {
            s.removeComponent(pid);
        }
    }

    public static void addComponent(int pid, String componentName) {
    		collision.update(EntityManager.getEntities());
    		for(ISystem s : systems) {
			s.addComponent(pid, componentName);
		}
    }
    public static void removeComponent(int pid, String componentName) {
    		collision.update(EntityManager.getEntities());
    		for(ISystem s : systems) {
    			s.removeComponent(pid, componentName);
    		}
    }
    
    public void setActives (Set<Integer> actives) {
        for (ISystem s : systems) {
            s.setActives(actives);
        }
    }

    public void execute (double time) {
        for (ISystem s: systems) {
            s.execute(time);
        }
    }

}
