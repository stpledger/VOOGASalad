package engine.setup;

import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.systems.ISystem;
import engine.systems.collisions.Collision;
import engine.components.Component;

/**
 * This is the class which delegates actions and components to the systems, allowing an efficient distribution of
 * tasks through this manager such that other classes do not need to hold instances of the systems. Makes adding
 * components (and entities) a smooth process along with running the execute methods.
 *
 * @author cndracos
 */
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

    /**
     * Method run when setting up the system. Passes the entity and all its components so that the
     * systems can pick from the option the relevant components, if any exist
     * @param pid the entity's ID
     * @param components its components
     */
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

    /**
     * Sets the active components for the systems to act upon based on the rendering
     * @param actives set of active components within rendering distance
     */
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
