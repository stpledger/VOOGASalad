package data;

import engine.components.Component;
import frontend.components.Level;
import frontend.entities.Entity;
import frontend.gamestate.GameState;

import java.util.*;
public class DataGameState {
    private Map<Level,Map<Integer, Map<String, Component>>> gameState;
    /*@Author Conrad this class represents the complete information that is contained in a 
     * game-- all levels which contain entities which contain all components 
     * additionally, this class provides utility methods for converting between authoring and player
     */
    
    /*creates a DataGameState from a map sent in from engine or player 
     */
    public DataGameState(Map<Level,Map<Integer, Map<String, Component>>> gameState) {
        this();
        this.gameState = gameState;
    }

    /*constructor that converts an authoring environment state to a state playable 
     * by player and engine
     */
    public DataGameState(GameState gameState) {
        this();
        for(Level level : gameState.getLevels()) {
            Map<Integer, Map<String, Component>> entityMap = new HashMap<>();
            for (Entity entity : level.getEntityList()) {
                Map<String, Component> componentMap = new HashMap<>();
                for (Component component :entity.getComponentList()) {
                    componentMap.put(component.getKey(), component);
                }
                entityMap.put(entity.getID(),componentMap);
            }
            this.gameState.put(level,entityMap);
        }
    }

    /*creates an empty gamestate which holds the place of a null values
     * useful for errors when loading gamefiles
     */
    public DataGameState(){
        this.gameState = new HashMap<Level,Map<Integer, Map<String, Component>>>();
    }

    /*returns a map in a form that player and authoring engine can take 
     */
    public Map<Level,Map<Integer, Map<String, Component>>> getGameState()
    {
        return gameState;
    }

    /*creates a map that represents the form of authoring's 
     * game state-- useful for conversion between gamestates
     */
    public  Map<Level, Map<Integer,List<Component>>> getGameStateAuthoring() 
    {
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
