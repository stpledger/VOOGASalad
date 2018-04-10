package data;

import engine.components.Component;
import frontend.gamestate.GameState;

import java.util.*;
public class DataGameState {
    private Map<Level,Map<Integer, Map<String, Component>>> gameState;

    public DataGameState(Map<Level,Map<Integer, Map<String, Component>>> gameState) {
        this();
        this.gameState = gameState;
    }

    public DataGameState(GameState gameState) {
        this();
        Map<Level, Map<Integer,List<Component>>> tempState = gameState.getAuthorMap();
        for(Level level : tempState.keySet()) {
            Map<Integer, Map<String, Component>> entityMap = new HashMap<>();
            for (Integer integer : tempState.get(level).keySet()) {
                Map<String, Component> componentMap = new HashMap<>();
                for (Component component : tempState.get(level).get(integer)) {
                    componentMap.put(component.getKey(), component);
                }
                entityMap.put(integer,componentMap);
            }

            this.gameState.put(level,entityMap);
        }


    }

    public DataGameState()
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
