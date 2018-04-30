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
    private Map<Integer, Map<String, Component>> handledComponents;
    private Set<Integer> activeComponents;

    public ArtificialIntelligence() {
    	handledComponents = new HashMap<>();
    	activeComponents = new HashSet<>();
    }
    
    @Override
    public void addComponent(int pid, Map<String, Component> components) {
        if (components.containsKey(AI.KEY)) {
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
            AI ai = (AI) components.get(AI.KEY);
            ai.doAction(components); //calls the AI action
        }
    }


}
