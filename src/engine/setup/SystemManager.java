package engine.setup;

import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.systems.ISystem;
import engine.components.Component;

public class SystemManager {

    private List<ISystem> systems;

    public SystemManager (List<ISystem> systems) {
        this.systems = systems;
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

    /**
     * For next step. Not implemented now.
    public void addComponent(int pid, Map<String, Component> components) {
    		for (ISystem s : systems) {
            s.addComponent(pid, components);
        }
    }

    
    public void removeComponent(int pid, Map<String, Component> components) {
    		for (ISystem s : systems) {
    			s.removeComponent(pid, components);
    		}
    }
    **/
    
}
