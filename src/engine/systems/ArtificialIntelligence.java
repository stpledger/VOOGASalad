package engine.systems;

import engine.components.AI;
import engine.components.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * System which calls the AI components' actions if they are within rendering dinstance
 *
 * @author cndracos
 */
public class ArtificialIntelligence implements  ISystem{
    private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();
    private Set<Integer> activeComponents = new HashSet<>();

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
        Set<Integer> myActives = new HashSet<>(actives);
        myActives.retainAll(handledComponents.keySet());
        activeComponents = myActives;
    }

    @Override
    public void execute(double time) {
        for (int id : activeComponents) {
            Map<String, Component> components = handledComponents.get(id);
            AI ai = (AI) components.get(AI.KEY);
            ai.doAction(time); //calls the AI action
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
