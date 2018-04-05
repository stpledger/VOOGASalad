package engine.systems;

import engine.components.Component;

import java.util.Map;

public interface ISystem {

    void addComponent (int pid, Map<String, Component> components);
    void removeComponent (int pid);
    void execute();
}
