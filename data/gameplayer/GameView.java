package gameplayer;

import java.util.HashMap;
import java.util.Map;

import authoring.gamestate.Level;
import data.DataGameState;
import data.DataWrite;

import engine.components.*;
import engine.setup.GameInitializer;
import engine.setup.RenderManager;
import engine.setup.SystemManager;
import engine.systems.InputHandler;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

/**
 * Class that controls how the entity objects are displayed on the GamePlayer
 * @authors Ryan Fu & Scott Pledger
 *
 */
public class GameView implements gameplayer.IGamePlayerView {
	private DataGameState gameState;

	private Map<Level,Map<Integer,Map<String,Component>>> levels;
	private Map<Integer, Map<Integer,Map<String,Component>>> intLevels;
	private Map<Integer, Pane> gameLevelDisplays;

	private GameInitializer gameInitializer;
	private InputHandler inputHandler;
	private RenderManager renderManager;
	private SystemManager systemManager;

	private int numOfLevels;
	private int activeLevel;
	private XPosition activePlayerPosX;
	private YPosition activePlayerPosY;

	private static final double PANE_HEIGHT = 442;
	private static final double PANE_WIDTH = 800;
	private static final int LEVEL_ONE = 1;
	private static final int TOP_BOUND = 100;
	private static final int BOTTOM_BOUND = 200;
	private static final int LEFT_BOUND = 100;
	private static final int RIGHT_BOUND = 400;
	private static final int INVERT = -1;

	private Map<Integer, Map<String, Component>> playerKeys;
	private Map<Integer, Map<String, Boolean>> hudPropMap;
	//TODO: TALK TO STEFANI ABOUT NEW "LEVEL STATUS" CLASS
	//private ArrayList<Win> winComponents;

	/**
	 * Constructor when given the gameState
	 * @param gamestate
	 */
	public GameView(DataGameState gamestate) {
		gameState = gamestate;
		levels = gameState.getGameState();
		hudPropMap = obtainHudProps(levels);
		playerKeys = new HashMap<>();
		intLevels = levelToInt(levels);
		numOfLevels = intLevels.keySet().size();
		gameLevelDisplays = createLevelDisplays(levels);
		setActiveLevel(LEVEL_ONE);
		initializeGameView();
	}

	//TODO: CHECK IF OBSELETE WITH NEW "LEVEL STATUS" CLASS
	/**
	 * Obtains heads-up display status map for each level in a game.
	 * @return Map<Integer, Map<String, Boolean>> 
	 */
	public Map<Integer, Map<String, Boolean>> getHudPropMap(){
		return hudPropMap;
	}

	/**
	 * Converts Map of levels to its Entities to Integers to Entities to make calling a particular level easier
	 * @return Map<Integer, Map<Integer,Map<String,Component>>> 
	 */
	public Map<Integer, Map<Integer,Map<String,Component>>> levelToInt(Map<Level, Map<Integer,Map<String,Component>>> levelMap) {
		Map<Integer, Map<Integer,Map<String,Component>>> temp = new HashMap<>();
		for(Level level: levelMap.keySet()){
			temp.put(level.getLevelNum(), levelMap.get(level));
		}
		return temp;
	}

	/**
	 * returns the display panes for each level
	 * @return
	 */
	public Map<Integer, Pane> getGameLevelDisplays(){
		return gameLevelDisplays;
	}


	/**
	 * Connects View to Engine Systems (GameInitializer, InputHandler, RenderManager, SystemManager)
	 */
	public void initializeGameView() {
		gameInitializer = new GameInitializer(intLevels.get(activeLevel),
				Math.max(PANE_HEIGHT, PANE_WIDTH), activePlayerPosX.getData(), activePlayerPosY.getData());
		inputHandler = gameInitializer.getInputHandler();
		renderManager = gameInitializer.getRenderManager();
		systemManager = gameInitializer.getSystemManager();
	}

	/**
	 * Set which level you want to display on the screen
	 * @param activelevel - level you wish to display
	 */
	public void setActiveLevel(int activelevel){
		activeLevel = activelevel;
		Map<String, Component> player = new HashMap<>(playerKeys.get(activeLevel));
		activePlayerPosX = (XPosition) player.get(XPosition.KEY);
		activePlayerPosY = (YPosition) player.get(YPosition.KEY);
	}

	/**
	 * Delegates actions and components to the systems from the view
	 * @param time
	 */
	public void execute (double time) {
		systemManager.execute(time);
	}

	/**
	 * Renders all changes to the objects sprite
	 */
	public void render() {
		double newCenterX = activePlayerPosX.getData();
		double newCenterY = activePlayerPosY.getData();
		systemManager.setActives(renderManager.render(newCenterX, newCenterY));
	}

	/**
	 * Pass user input to the engine
	 * @param code
	 */
	public void setInput(KeyCode code){
		inputHandler.addCode(code);
	}

	/**
	 * When the key is released, remove it from the input handler
	 * @param code
	 */
	public void removeInput (KeyCode code) {
		inputHandler.removeCode(code);
	}

	/**
	 * Returns a map of the player components for each level
	 * @return
	 */
	public Map<Integer, Map<String, Component>> getPlayerKeys(){
		return playerKeys;
	}

	/**
	 * Saves the current game state to a new file
	 */
	public void saveGame(){
		DataWrite dw = new DataWrite();
		dw.saveGame(gameState, "test");
	}

	/**
	 * Updates the view of the Pane so that it scrolls with the player's movement. Allows for some free movement without scrolling
	 * @param gameRoot
	 */
	public void updateScroll(Pane gameRoot){
		double minX = gameRoot.getTranslateX() * INVERT;
		double maxX = gameRoot.getTranslateX() * INVERT + PANE_WIDTH;
		double minY = gameRoot.getTranslateY() * INVERT;
		double maxY = gameRoot.getTranslateY() * INVERT + PANE_HEIGHT;

		if(activePlayerPosY.getData() - TOP_BOUND < minY){
			gameRoot.setTranslateY((activePlayerPosY.getData() - TOP_BOUND) * INVERT);
		}

		if(activePlayerPosY.getData() + BOTTOM_BOUND > maxY){
			gameRoot.setTranslateY(((activePlayerPosY.getData() + BOTTOM_BOUND) - PANE_HEIGHT) * INVERT);
		}

		if(activePlayerPosX.getData() - LEFT_BOUND < minX){
			gameRoot.setTranslateX((activePlayerPosX.getData() - LEFT_BOUND) * INVERT);
		}

		if(activePlayerPosX.getData() + RIGHT_BOUND > maxX){
			gameRoot.setTranslateX(((activePlayerPosX.getData() + RIGHT_BOUND) - PANE_WIDTH) * INVERT);
		}
	}

	/**
	 * Returns current active level
	 * @return
	 */
	public int getActiveLevel(){
		return activeLevel;
	}

	/**
	 * Returns the number of levels
	 * @return
	 */
	public int getNumOfLevels(){
		return numOfLevels;
	}

	//TODO: IS THIS NECESSARY?
	/*public ArrayList<Win> getWinComponents() {
		return winComponents;
	}*/
	
	/**
	 * Method to obtain the map of heads-up display properties for each level.
	 * @param levels
	 * @return Map<Integer, Map<String,Boolean>>
	 */
	private Map<Integer, Map<String, Boolean>> obtainHudProps(Map<Level,Map<Integer,Map<String,Component>>> levels){
		Map<Integer, Map<String, Boolean>> HUDPropMap = new HashMap<Integer, Map<String, Boolean>>();
		int count = 1;
		for (Level l: levels.keySet()) {
			HUDPropMap.put(count, l.getHUDprops());
			count++;
		}
		return HUDPropMap;
	}
	
	/**
	 * Method that builds the entire map of level with groups of sprite images
	 * @param map
	 *
	 */
	private Map<Integer, Pane> createLevelDisplays(Map<Level, Map<Integer, Map<String, Component>>> map){
		Map<Integer, Pane> levelEntityMap = new HashMap<>();
		for(Level level : map.keySet()) {
			levelEntityMap.put(level.getLevelNum(), createIndividualEntityGroup(map.get(level), level.getLevelNum()));
		}
		return levelEntityMap;
	}

	/**
	 * Method that creates all the groups for each level in a levels.
	 * @param entityMap
	 * @return
	 */
	private Pane createIndividualEntityGroup(Map<Integer, Map<String, Component>> entityMap, int levelNum) {
		Pane entityRoot = new Pane();
		Map<String, Component> entityComponents;
		for(Integer i : entityMap.keySet()) {
			entityComponents = entityMap.get(i);
			if(entityComponents.containsKey(Sprite.KEY)) {
				Sprite spriteComponent = (Sprite) entityComponents.get(Sprite.KEY);
				ImageView image = spriteComponent.getImage();

				if (entityComponents.containsKey(XPosition.KEY) && entityComponents.containsKey(YPosition.KEY)) {
					setSpritePosition(entityComponents, image);

					//TODO: IS THIS NECESSARY AFTER THE NEW "LEVEL STATUS" CLASS?
					if(entityComponents.containsKey(Player.KEY)){
						playerKeys.put(levelNum, entityComponents);
					}

					//TODO: IS THIS NECESSARY AFTER THE NEW "LEVEL STATUS" CLASS?
					if (entityComponents.containsKey(Win.KEY)) {
						//winComponents.add((Win) entityComponents.get(Win.KEY));
					}
				}

				if(entityComponents.containsKey(Width.KEY) && entityComponents.containsKey(Height.KEY)) {
					setSpriteSize(entityComponents, image);
				}

				entityRoot.getChildren().add(image);
			}
		}
		return entityRoot;
	}

	/**
	 * Repositions the sprite image on the screen based on its position component
	 * @param entityComponents - entity
	 * @param image - sprite
	 */
	private void setSpritePosition(Map<String, Component> entityComponents, ImageView image){
		XPosition px = (XPosition) entityComponents.get(XPosition.KEY);
		YPosition py = (YPosition) entityComponents.get(YPosition.KEY);
		image.setX(px.getData());
		image.setY(py.getData());
	}

	/**
	 * Resizes the sprite image on the screen based on its width and height components
	 * @param entityComponents
	 * @param image
	 */
	private void setSpriteSize(Map<String, Component> entityComponents, ImageView image){
		Width w = (Width) entityComponents.get(Width.KEY);
		Height h = (Height) entityComponents.get(Height.KEY);
		image.setFitHeight(h.getData());
		image.setFitWidth(w.getData());
	}

}
