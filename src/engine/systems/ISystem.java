package engine.systems;

import engine.components.Component;

import java.util.Map;
import java.util.Set;
/*
 * interface used by all systems
 * author sv116
 */
public interface ISystem {
	void addComponent (int pid, Map<String, Component> components);
    void removeComponent (int pid);
    void setActives(Set<Integer> actives);
    void execute(double time);

}
