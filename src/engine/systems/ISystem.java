package engine.systems;

import engine.components.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISystem {

    void addComponent (int pid, Map<String, Component> components);
    void removeComponent (int pid);
    void setActives(Set<Integer> actives);
    void execute(double time);
    Map<Integer, List<Component>> getAllComponents();
}
