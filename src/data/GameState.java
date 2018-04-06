package data;

import engine.components.Component;

import java.util.HashMap;
import java.util.Map;

public class GameState {
    private Map<Integer, Map<String, Component>> gameState = new HashMap<Integer, Map<String, Component>>();

    public GameState(Map<Integer, Map<String, Component>> gameState)
    {
        this();
        this.gameState = gameState;
    }

    public GameState()
    {
        this.gameState = new HashMap<Integer, Map<String, Component>>();
    }


    public Map<Integer, Map<String, Component>> getGameState()
    {
        return gameState;
    }
}
