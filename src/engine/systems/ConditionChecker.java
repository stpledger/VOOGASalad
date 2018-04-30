package engine.systems;

import engine.components.Component;
import engine.components.Conditional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConditionChecker implements ISystem {
    private Map<Integer, Map<String, Component>> handledComponents;
    private Set<Integer> activeComponents;

    public ConditionChecker() {
    	handledComponents = new HashMap<>();
    	activeComponents = new HashSet<>();
    }

    @Override
    public void addComponent(int pid, Map<String, Component> components) {
        if (components.containsKey(Conditional.KEY)) {
            handledComponents.put(pid, components);
        }
    }

    @Override
    public void removeComponent(int pid) {
        if(handledComponents.containsKey(pid)) {
            handledComponents.remove(pid);
        }
    }

    @Override
    public void setActives(Set<Integer> actives) {
        Set<Integer> myActives = new HashSet<>(actives);
        myActives.retainAll(handledComponents.keySet());
        activeComponents = myActives;
    }

    @Override
    public void execute(double time) {
        for (int id : activeComponents) {
            Map<String, Component> components = handledComponents.get(id);
            Conditional c = (Conditional) components.get(Conditional.KEY);
            c.evaluate();
        }
    }


}
