package gameplayer;

import authoring.gamestate.Level;
import data.DataGameState;
import engine.components.*;

import java.util.HashMap;
import java.util.Map;

/**
 * GameManager extracts all of the information needed to manage updating the state of the game by Controller and GameView
 * @author Scott Pledger
 */
public class GameManager {
    private Map<Integer, Map<String, Component>> playerKeys;
    private Map<Win, Integer> winKeys;
    private int activeLevel;
    private XPosition activePlayerPosX;
    private YPosition activePlayerPosY;
    private int numOfLevels;

    private static final int FIRST_LEVEL = 1;

    public GameManager(DataGameState gameState){
        Map<Level, Map<Integer, Map<String, Component>>> levelMap = gameState.getGameState();
        playerKeys = new HashMap<>();
        winKeys = new HashMap<>();

        for(Level level : levelMap.keySet()){
            extractInfo(levelMap.get(level), level.getLevelNum());
        }

        numOfLevels = levelMap.keySet().size();

        setActiveLevel(FIRST_LEVEL);
    }

    /**
     * Extracts player and win components from each level
     * @param entities
     * @param levelNum
     */
    private void extractInfo(Map<Integer, Map<String, Component>> entities, int levelNum){
        Map<String, Component> entityComponents;
        for(Integer i : entities.keySet()) {
            entityComponents = entities.get(i);
            if(entityComponents.containsKey(Sprite.KEY)) {
                if(entityComponents.containsKey(Player.KEY)){
                    playerKeys.put(levelNum, entityComponents);
                }
                if (entityComponents.containsKey(Win.KEY)) {
                    winKeys.put((Win) entityComponents.get(Win.KEY), levelNum);
                }
            }
        }
    }

    /**
     * Get a map of Level Number to their corresponding Player entity
     * @return
     */
    public Map<Integer, Map<String, Component>> getPlayerKeys() {
        return playerKeys;
    }

    /**
     * Get a map of Win components to their corresponding levels
     * @return
     */
    public Map<Win, Integer> getWinKeys() {
        return winKeys;
    }

    /**
     * Get the value of the level currently being played
     * @return
     */
    public int getActiveLevel() {
        return activeLevel;
    }

    /**
     * Get the XPosition of the Player in the current active level as a double
     * @return
     */
    public double getActivePlayerPosX() {
        return activePlayerPosX.getData();
    }

    /**
     * Get the YPosition of the Player in the current active level as a double
     * @return
     */
    public double getActivePlayerPosY() {
        return activePlayerPosY.getData();
    }

    /**
     * Get the total number of levels
     * @return
     */
    public int getNumOfLevels() {
        return numOfLevels;
    }

    /**
     * Set the value of activeLevel
     * @param level
     */
    public void setActiveLevel(int level) {
        this.activeLevel = level;
        if(level <= this.numOfLevels){
            this.activePlayerPosX = (XPosition) playerKeys.get(level).get(XPosition.KEY);
            this.activePlayerPosY = (YPosition) playerKeys.get(level).get(YPosition.KEY);
        }
    }

}