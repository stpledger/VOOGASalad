package engine.systems;

import engine.components.AI;
import engine.components.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ArtificialIntelligence implements  ISystem{
    private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();

    @Override
    public void addComponent(int pid, Map<String, Component> components) {
        if (components.containsKey(AI.KEY)) {
            Map<String, Component> newComponents = new HashMap<>();
            newComponents.put(AI.KEY,components.get(AI.KEY));
            handledComponents.put(pid, newComponents);
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
        handledComponents.keySet().retainAll(actives);
    }

    @Override
    public void execute(double time) {
        for (int id : handledComponents.keySet()) {
            Map<String, Component> components = handledComponents.get(id);
            AI ai = (AI) components.get(AI.KEY);
            ai.doAction(time);
        }
    }

    @Override
    public void addComponent(int pid, String componentName) {
        //get back to this
    }

    @Override
    public void removeComponent(int pid, String componentName) {
        //get back to this
    }

    @Override
    public Map<Integer, Map<String, Component>> getHandledComponent() {
        return handledComponents;
    }
}
