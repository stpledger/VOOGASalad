package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import authoring.gamestate.Level;
import data.DataGameState;
import data.DataRead;
import data.DataWrite;
import engine.components.*;
import engine.components.Component;
import engine.components.Dimension;
import engine.setup.EntityManager;
import engine.setup.GameInitializer;
import engine.setup.RenderManager;
import engine.setup.SystemManager;
import engine.systems.InputHandler;
import engine.systems.collisions.LevelStatus;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
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
	private Map<Level,Map<Integer,Map<String,Component>>> levelMap;
	private Map<Integer, Map<Integer,Map<String,Component>>> intLevelMap;
	private Map<Integer, Map<String, Component>> entityMap;
	private Map<Integer, Pane> levelEntityMap;
	private DataGameState gameState;
	private File gameFile;
	private GameInitializer gameInitializer;
	private InputHandler inputHandler;
	private RenderManager renderManager;
	private SystemManager systemManager;
	private LevelStatus LS;

	private int ActiveLevel;
	private Position ActivePlayerPos;

	private Map<Integer, Map<String, Component>> PlayerKeys;

	public GamePlayerEntityView(File file) throws FileNotFoundException {
		gameFile = file;
		gameState = DataRead.loadPlayerFile(gameFile);
		levelMap = gameState.getGameState();
		PlayerKeys = new HashMap<>();
		levelToInt();
		levelEntityMap = createEntityGroupMap(levelMap);
		System.out.println(levelMap.size());
		int count = 0;
		/*for(Level level : levelMap.keySet()) {
			entityMap = levelMap.get(level);  //currently entityMap is the first level map of integer to components
			//intLevelMap.put(1, entityMap);
			break;
//			count++;
		}*/
		setActiveLevel(1);
		initializeGamePlayerEntityView();
	}

	/**
	 * Converts Map of Levels to its Entities to Integers to Entities to make calling a particular level easier
	 */
	public void levelToInt() {
		intLevelMap = new HashMap<>();
		for(Level level: levelMap.keySet()){
			intLevelMap.put(level.getLevelNum(), levelMap.get(level));
		}
	}

	/**
	 * returns the levelEntityMap;
	 * @return
	 */
	public Map<Integer, Pane> getlevelEntityMap(){
		return levelEntityMap;
	}

	/**
	 * Method that builds the entire map of level with groups of sprite images
	 * @param levelMap 
	 * 
	 */
	private Map<Integer, Pane> createEntityGroupMap(Map<Level, Map<Integer, Map<String, Component>>> levelMap){
		int count = 1;
		Map<Integer, Pane> levelEntityMap = new HashMap<>();
		for(Level level : levelMap.keySet()) {
			levelEntityMap.put(count, createIndividualEntityGroup(levelMap.get(level), count));
			//levelEntityMap.put(count+1, createIndividualEntityGroup(levelMap.get(level))); //TESTING DELETE
			System.out.println(levelEntityMap.get(count));
			count++;
		}
		return levelEntityMap;
	}

	/**
	 * Method that creates all the groups for each level in a levelMap.
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
		for(Level level : levelMap.keySet()) {
			if (count == levelNum) {
				
				currentLevel = levelMap.get(level);
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
			gameInitializer = new GameInitializer(intLevelMap.get(ActiveLevel));
			//gameInitializer = new GameInitializer(intLevelMap.get(0)); //gets the first level map.
		} catch (FileNotFoundException e) {
			System.out.println("entityMap not initialized");
		}

		inputHandler = gameInitializer.getIH();
		renderManager = gameInitializer.getRM();
		systemManager = gameInitializer.getSM();

		//added code for listening if level should change, not sure this is the best place to put it, but it works
		LS = gameInitializer.getC().getCH().getLS();
		LS.getUpdate().addListener((o,oldVal,newVal) -> {
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
		gameRoot.setTranslateY(ActivePlayerPos.getYPos() * -1 + 250);
    }

}
