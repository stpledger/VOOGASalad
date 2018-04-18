package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import data.DataGameState;
import data.DataRead;
import engine.components.*;
import engine.setup.EntityManager;
import engine.setup.GameInitializer;
import engine.setup.RenderManager;
import engine.setup.SystemManager;
import engine.systems.InputHandler;
import frontend.components.Level;
import frontend.entities.Entity;
import javafx.scene.Group;
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
<<<<<<< HEAD
	private File gameFile;

	private GameInitializer GI;
=======
	private GameInitializer gameInitializer;
>>>>>>> aad2f0ace99a522a363ca65887e1d73b45223b7a
	private InputHandler inputHandler;
	private RenderManager RM;

	public GamePlayerEntityView(File file) throws FileNotFoundException {
		gameFile = file;
		gameState = DataRead.loadFile(gameFile);
		levelMap = gameState.getGameState();
		levelEntityMap = createEntityGroupMap(levelMap);

<<<<<<< HEAD
		for (Level l : levelMap.keySet()) {
			entityMap = levelMap.get(l);
=======
		for(Level level : levelMap.keySet()) {
			entityMap = levelMap.get(level);  //currently entityMap is the first level map of integer to components
>>>>>>> aad2f0ace99a522a363ca65887e1d73b45223b7a
			break;
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
			levelEntityMap.put(count+1, createIndividualEntityGroup(levelMap.get(level))); //TESTING DELETE
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
				System.out.print(image.getX());
				entityRoot.getChildren().add(image);
			}
		}
		//entities that have sprites and setup sprite images
		return entityRoot;
	}
	//**************************************************************************


	/**
	 * initialize the Game Initializer to create the systemManager and renderManager.
	 * @throws FileNotFoundException
	 */
	public void initializeGamePlayerEntityView() {

		try {
			GI  = new GameInitializer(entityMap);
		} catch (FileNotFoundException e) {
			System.out.println("You made it this far");
			e.printStackTrace();
		}

		inputHandler = GI.getIH();
		RM = GI.getRM();
	}

	public void execute (double time) {
		SystemManager.execute(time);
	}

	public void render() {
		RM.renderObjects();
		RM.garbageCollect();
	}

	public void setInput(KeyCode code){
		inputHandler.addCode(code);
	}

	public void removeInput (KeyCode code) {
		inputHandler.removeCode(code);
	}

}
