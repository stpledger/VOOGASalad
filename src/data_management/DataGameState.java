package data_management;

import engine.components.Component;

import java.util.*;

import authoring.entities.Entity;
import authoring.gamestate.GameState;
import authoring.gamestate.Level;
/**
 * @author Conrad 
 * @author Collin Brown(cdb55)
 */
public class DataGameState {
    private Map<Level,Map<Integer, Map<String, Component>>> gameState;
    private String gameName = "data";

    /*@Author Conrad this class represents the complete information that is contained in a 
     * game-- all levels which contain entities which contain all components 
     * additionally, this class provides utility methods for converting between authoring and player
     */
    
    /*creates a DataGameState from a map sent in from engine or player 
     */
    public DataGameState(Map<Level,Map<Integer, Map<String, Component>>> gameState, String gName) {
        this(gName);
        this.gameState = gameState;
    }

    /*constructor that converts an authoring environment state to a state playable 
     * by player and engine
     */
    public DataGameState(GameState gameState, String gName) {
        this(gName);
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
    public DataGameState(String gName){
        this.gameState = new HashMap<Level,Map<Integer, Map<String, Component>>>();
        gameName = gName;
    }

    /*returns a map in a form that player and authoring engine can take
     */
    public Map<Level,Map<Integer, Map<String, Component>>> getGameState() {
        return gameState;
    }

    /*creates a map that represents the form of authoring's 
     * game state-- useful for conversion between gamestates TODO clean this up
     */
    public  Map<Level, Map<Integer,List<Component>>> getGameStateAuthoring() {
        Map<Level, Map<Integer,List<Component>>> authoringState = new HashMap<>();

        for(Level level : gameState.keySet()) {
            Map<Integer, List<Component>> entityMap = new HashMap<>();
            for (Integer integer : gameState.get(level).keySet()) {
                List<Component> components = new ArrayList(gameState.get(level).get(integer).values());
                entityMap.put(integer, components);
                authoringState.put(level,entityMap);
            }
        }
        return authoringState;
    }

    //gets name gets .... the name of the file
	public String getGameName()
    {
        return gameName;
    }

    //returns the list of all components in the gamestate for data writing
    public List<Component> getComponents() {
        List<Component> componentList = new ArrayList<>();
        for(Map<Integer, Map<String,Component>> level : gameState.values())
            for(Map<String,Component> entity : level.values())
               componentList.addAll(entity.values());
        return componentList;
    }

	
}
