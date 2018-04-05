package engine.setup;

import java.util.List;
import java.util.Map;

import engine.systems.ISystem;
import engine.components.Component;

public class SystemManager {

    private List<ISystem> systems;

    public SystemManager (List<ISystem> systems) {
        this.systems = systems;
    }

    public void addComponents (int pid, Map<String, Component> components) {
        for (ISystem s : systems) {
            s.addComponent(pid, components);
        }
    }

    public void removeComponents (int pid) {
        for (ISystem s : systems) {
            s.removeComponent(pid);
        }
    }

    public void execute (double time) {
        for (ISystem s: systems) {
            s.execute(time);
        }
    }
}
