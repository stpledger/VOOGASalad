package gameplayer.view;

import engine.components.Component;

import java.util.Map;

public interface EntityManager {
    void addEntity(int pid, Map<String, Component> entity);

    void removeEntity(int pid);
}
