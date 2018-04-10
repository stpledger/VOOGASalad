package data;

import engine.components.Component;

import java.util.*;
import java.util.logging.Level;

public class GameState {
    private Map<Level,Map<Integer, Map<String, Component>>> gameState;

    public GameState(Map<Level,Map<Integer, Map<String, Component>>> gameState) {
        this();
        this.gameState = gameState;
    }

    public GameState(Map<Level, Map<Integer,List<Component>>> gameState) {
        this();
        for(Level level : gameState.keySet()) {
            Map<Integer, Map<String, Component>> entityMap = new HashMap<>();
            for (Integer integer : gameState.get(level).keySet()) {
                Map<String, Component> componentMap = new HashMap<>();
                for (Component component : gameState.get(level).get(integer)) {
                    componentMap.put(component.getKey(), component);
                }
                entityMap.put(integer,componentMap);
            }
            this.gameState.put(level,entityMap);
        }


    }

    public GameState()
    {
        this.gameState = new HashMap<Level,Map<Integer, Map<String, Component>>>();
    }

    public Map<Level,Map<Integer, Map<String, Component>>> getGameState()
    {
        return gameState;
    }

    public Map<Level,Map<Integer,List<Component>>> getGameStateAuthoring() {
        Map<Level, Map<Integer,List<Component>>> authoringState = new HashMap<>();
        for(Level level : gameState.keySet()) {
            for (Integer integer : gameState.get(level).keySet()) {
                Map<Integer, List<Component>> componentList = new HashMap<>();
                List<Component> components = new ArrayList(gameState.get(level).get(integer).values());
                componentList.put(integer, components);
                authoringState.put(level,componentList);
            }
        }
        return authoringState;
    }
}
