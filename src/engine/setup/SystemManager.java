package engine.setup;

import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.systems.ISystem;
import engine.systems.collisions.Collision;
import engine.components.Component;

public class SystemManager {

    private List<ISystem> systems;
    private Collision collision;
    private EntityManager em;
    
    public SystemManager (List<ISystem> systems, Collision collision, EntityManager em) {
        this.systems = systems;
        this.collision = collision;
        this.em = em;
    }

    public void addSystem(ISystem system) {
    		systems.add(system);
    }
    
    public void addEntity(int pid, Map<String, Component> components) {
        for (ISystem s : systems) {
            s.addComponent(pid, components);
        }
    }

    public void removeEntity (int pid) {
        for (ISystem s : systems) {
            s.removeComponent(pid);
        }
    }

    public void addComponent(int pid, String componentName) {
    		collision.update(em.getEntities());
    		for(ISystem s : systems) {
			s.addComponent(pid, componentName);
		}
    }
    public void removeComponent(int pid, String componentName) {
    		collision.update(em.getEntities());
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
