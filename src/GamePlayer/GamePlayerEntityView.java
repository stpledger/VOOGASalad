package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import authoring.gamestate.Level;
import data.DataGameState;
import data.DataRead;
import data.DataWrite;
import engine.components.*;
import engine.components.Component;
import engine.components.Dimension;
import engine.setup.GameInitializer;
import engine.setup.RenderManager;
import engine.setup.SystemManager;
import engine.systems.InputHandler;
import engine.systems.collisions.LevelStatus;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;


/**
 * Class that controls how the entity objects are displayed
 * @author Ryan Fu
 *
 */
public class GamePlayerEntityView {
	//private Group entityRoot;
	private Map<Level,Map<Integer,Map<String,Component>>> Levels;
	private Map<Integer, Map<Integer,Map<String,Component>>> IntLevels;
	private Map<Integer, Map<String, Component>> ActiveEntities;
	private Map<Integer, Pane> LevelDisplays;
	private DataGameState gameState;
	private File gameFile;
	private GameInitializer gameInitializer;
	private InputHandler inputHandler;
	private RenderManager renderManager;
	private SystemManager systemManager;
	private LevelStatus levelStatus;

	private int ActiveLevel;
	private Position ActivePlayerPos;

	private static final double PANE_HEIGHT = 442;
	private static final double PANE_WIDTH = 800;

	// RYAN THIS IS WHAT YOU NEED TO IMPLEMENT HUD VALUES
	private Map<Integer, Map<String, Component>> PlayerKeys;

	public GamePlayerEntityView(File file) throws FileNotFoundException {
		gameFile = file;
		gameState = DataRead.loadPlayerFile(gameFile);
		Levels = gameState.getGameState();
		PlayerKeys = new HashMap<>();
		levelToInt();
		LevelDisplays = createEntityGroupMap(Levels);
		setActiveLevel(1);
		initializeGamePlayerEntityView();
	}

	/**
	 * Converts Map of Levels to its Entities to Integers to Entities to make calling a particular level easier
	 */
	public void levelToInt() {
		IntLevels = new HashMap<>();
		for(Level level: Levels.keySet()){
			IntLevels.put(level.getLevelNum(), Levels.get(level));
		}
	}

	/**
	 * returns the levelEntityMap;
	 * @return
	 */
	public Map<Integer, Pane> getlevelEntityMap(){
		return LevelDisplays;
	}

	/**
	 * Method that builds the entire map of level with groups of sprite images
	 * @param map
	 * 
	 */
	private Map<Integer, Pane> createEntityGroupMap(Map<Level, Map<Integer, Map<String, Component>>> map){
		int count = 1;
		Map<Integer, Pane> levelEntityMap = new HashMap<>();
		for(Level level : map.keySet()) {
			levelEntityMap.put(count, createIndividualEntityGroup(map.get(level), count));
			//levelEntityMap.put(count+1, createIndividualEntityGroup(Levels.get(level))); //TESTING DELETE
			count++;
		}
		return levelEntityMap;
	}

	/**
	 * Method that creates all the groups for each level in a Levels.
	 * @param entityMap
	 * @return
	 */

	public Pane createIndividualEntityGroup(Map<Integer, Map<String, Component>> entityMap, int levelNum) {
		Pane entityRoot = new Pane();
		Map<String, Component> entityComponents;
		//Changed enclosed code to only load sprites for 
		for(Integer i : entityMap.keySet()) {
			entityComponents = entityMap.get(i);
			if(entityComponents.containsKey(Sprite.KEY)) {
				Sprite spriteComponent = (Sprite) entityComponents.get(Sprite.KEY);
				ImageView image = spriteComponent.getImage(); //gets the class of the sprite
				//				image.setX(200);
				//				image.setY(200);
				//image.setImage(new Image("mystery.jpg"));
				//System.out.print(image.getX());
				if (entityComponents.containsKey(Position.KEY)) {
					Position p = (Position) entityComponents.get(Position.KEY);
					image.setX(p.getXPos());
					image.setY(p.getYPos());

					// setting up values to track for window scroll
					if(entityComponents.containsKey(Player.KEY)){
                        PlayerKeys.put(levelNum, entityComponents);
                    }
				}
				
				//	JACK ADDED THIS .............
				
				if(entityComponents.containsKey(Dimension.KEY)) {
					Dimension dim = (Dimension) entityComponents.get(Dimension.KEY);
					image.setFitHeight(dim.getHeight());
					image.setFitWidth(dim.getWidth());
				}
				
				//	Sizes images correctly	.................
				
				
				//System.exit(0);
				entityRoot.getChildren().add(image);
			}
		}
		//entities that have sprites and setup sprite images
		return entityRoot;
	}
	//**************************************************************************

	/**
	 * When a level change is invoked, reinitalize the GameInitializer to add functionality.
	 * @param levelNum
	 */
	public void reinitializeGameEngine(int levelNum) {
		int count = 1;
		Map<Integer, Map<String, Component>> currentLevel = null;
		for(Level level : Levels.keySet()) {
			if (count == levelNum) {
				
				currentLevel = Levels.get(level);
				System.out.println(currentLevel);
				break;
			}
			count++;
		}
		try {
			System.out.println(currentLevel);
			gameInitializer = new GameInitializer(currentLevel); //reinitializes the level.
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Level Does Not Currently Exist Yet");
		} 
	}

	/**
	 * initialize the Game Initializer to create the systemManager and renderManager.
	 * @throws FileNotFoundException
	 */
	public void initializeGamePlayerEntityView() {

		try {
			gameInitializer = new GameInitializer(IntLevels.get(ActiveLevel));
			//gameInitializer = new GameInitializer(IntLevels.get(0)); //gets the first level map.
		} catch (FileNotFoundException e) {
			System.out.println("ActiveEntities not initialized");
		}

		inputHandler = gameInitializer.getIH();
		renderManager = gameInitializer.getRM();
		systemManager = gameInitializer.getSM();

		//added code for listening if level should change, not sure this is the best place to put it, but it works
		levelStatus = gameInitializer.getC().getCH().getLS();
		levelStatus.getUpdate().addListener((o, oldVal, newVal) -> {
	   //  some action based on the value of newVal like -1 game over, from 1 to 2 change to level two etc. 
	  });
	}

	public void setActiveLevel(int i){
		ActiveLevel = i;
		Map<String, Component> player = new HashMap<>(PlayerKeys.get(ActiveLevel));
		ActivePlayerPos = (Position) player.get(Position.KEY);
	}
	

	public void execute (double time) {
		systemManager.execute(time);
	}

	public void render() {
		renderManager.garbageCollect();
		systemManager.setActives(renderManager.renderObjects());
	}
    
	public void setInput(KeyCode code){
		inputHandler.addCode(code);
	}

	public void removeInput (KeyCode code) {
		inputHandler.removeCode(code);
	}

	public void saveGame(){
		DataWrite dw = new DataWrite();
		dw.saveGame(gameState, "test");
	}

	// used to update the bounds of the scrollpane so the view shifts with the user's character
	public void updateScroll(Pane gameRoot){
		double minX = gameRoot.getTranslateX();
		double maxX = gameRoot.getTranslateX() + PANE_WIDTH;
		double minY = gameRoot.getTranslateY() * -1;
		double maxY = gameRoot.getTranslateY() * -1 + PANE_HEIGHT;

		if(ActivePlayerPos.getYPos() - 100 < minY){
			gameRoot.setTranslateY((ActivePlayerPos.getYPos() - 100) * -1);
		}

		if(ActivePlayerPos.getYPos() + 200 > maxY){
			gameRoot.setTranslateY(((ActivePlayerPos.getYPos() + 200) - PANE_HEIGHT) * -1);
		}

		if(ActivePlayerPos.getXPos() - 100 < minX){
			gameRoot.setTranslateX((ActivePlayerPos.getXPos() - 100) * -1);
		}

		if(ActivePlayerPos.getXPos() + 400 > maxX){
			gameRoot.setTranslateX(((ActivePlayerPos.getXPos() + 400) - PANE_WIDTH) * -1);
		}
    }

}
