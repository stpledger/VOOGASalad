package GamePlayer;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import data.DataGameState;
import data.DataRead;
import data.DataWrite;
import engine.components.*;
import engine.components.Component;
import engine.setup.EntityManager;
import engine.setup.GameInitializer;
import engine.setup.RenderManager;
import engine.setup.SystemManager;
import engine.systems.InputHandler;
import frontend.components.Level;
import frontend.entities.Entity;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


/**
 * Class that controls how the entity objects are displayed
 * @author Ryan Fu
 *
 */
public class GamePlayerEntityView {
	private File gameFile;
	//private Group entityRoot;
	private Map<Level,Map<Integer,Map<String,Component>>> levelMap;
	private Map<Integer, Map<String, Component>> entityMap;
	private Map<Integer, Group> levelEntityMap;
	private DataGameState gameState;
	private GameInitializer gameInitializer;
	private InputHandler inputHandler;
	private RenderManager renderManager;
	private Map<Integer, Map<Integer,Map<String,Component>>> intLevelMap;

	public GamePlayerEntityView(File file) throws FileNotFoundException {
		gameFile = file;
		gameState = DataRead.loadFile(gameFile);
		levelMap = gameState.getGameState();
		levelEntityMap = createEntityGroupMap(levelMap);
		System.out.println(levelMap.size());
		int count = 0;
		for(Level level : levelMap.keySet()) {
			entityMap = levelMap.get(level);  //currently entityMap is the first level map of integer to components
			//intLevelMap.put(1, entityMap);
			break;
//			count++;
		}
		initializeGamePlayerEntityView();
	}

	/**
	 * returns the levelEntityMap;
	 * @return
	 */
	public Map<Integer, Group> getlevelEntityMap(){
		return levelEntityMap;
	}
	//**************************************************************************
	//TESTING PURPOSED FOR LEVEL SELECTOR

	/**
	 * Method that builds the entire map of level with groups of sprite images
	 * @param levelMap 
	 * 
	 */
	private Map<Integer, Group> createEntityGroupMap(Map<Level, Map<Integer, Map<String, Component>>> levelMap){
		int count = 1;
		Map<Integer, Group> levelEntityMap = new HashMap<>();

		for(Level level : levelMap.keySet()) {
			levelEntityMap.put(count, createIndividualEntityGroup(levelMap.get(level)));
			//levelEntityMap.put(count+1, createIndividualEntityGroup(levelMap.get(level))); //TESTING DELETE
			System.out.println(levelEntityMap.get(count));
			count++;
		}
		//Testing purposes
		//TEsting purposes
		return levelEntityMap;
	}

	/**
	 * Method that creates all the groups for each level in a levelMap.
	 * @param entityMap
	 * @return
	 */

	public Group createIndividualEntityGroup(Map<Integer, Map<String, Component>> entityMap) {
		Group entityRoot = new Group();
		Map<String, Component> entityComponents;
		//Changed enclosed code to only load sprites for 
		for(Integer i : entityMap.keySet()) {
			entityComponents = entityMap.get(i);
			if(entityComponents.containsKey("Sprite")) {
				Sprite spriteComponent = (Sprite) entityComponents.get("Sprite");
				ImageView image = spriteComponent.getImage(); //gets the class of the sprite
				//System.out.print(image.getX());
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
			gameInitializer = new GameInitializer(entityMap);
			//gameInitializer = new GameInitializer(intLevelMap.get(0)); //gets the first level map.
		} catch (FileNotFoundException e) {
			System.out.println("You made it this far");
			e.printStackTrace();
		}

		inputHandler = gameInitializer.getIH();
		renderManager = gameInitializer.getRM();
	}

	public void execute (double time) {
		SystemManager.execute(time);
	}

	public void render() {
		renderManager.renderObjects();
		renderManager.garbageCollect();
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
	public void updateScroll(Group gameRoot){
		//pane.setVvalue(pane.getVvalue() + 1);
		//System.out.println(pane.getHvalue());
		//pane.setHvalue(pane.getHvalue() + 1);
		//System.out.println(pane.getHvalue());
		gameRoot.setLayoutX(gameRoot.getTranslateX() + 1);
		//System.out.println(gameRoot.getTranslateX());
	}

}
