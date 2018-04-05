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

    public void addComponents (int id, Map<String, Component> components) {
        for (ISystem s : systems) {
            s.addComponent(id, components);
        }
    }

    public void removeComponents (int id) {
        for (ISystem s : systems) {
            s.removeComponent(id);
        }
    }
}
