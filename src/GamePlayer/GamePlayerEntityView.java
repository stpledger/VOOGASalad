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
import engine.components.groups.Dimension;
import engine.components.groups.Position;
import engine.components.Component;
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


/**
 * Class that controls how the entity objects are displayed
 * @author Ryan Fu
 *
 */
public class GamePlayerEntityView {
	//private Group entityRoot;
	private Map<Level,Map<Integer,Map<String,Component>>> levelMap;
	private Map<Integer, Map<String, Component>> entityMap;
	private Map<Integer, Group> levelEntityMap;
	private DataGameState gameState;
	private File gameFile;

	private GameInitializer GI;
	private InputHandler inputHandler;
	private RenderManager RM;
	private SystemManager SM;
    private LevelStatus LS;

	public GamePlayerEntityView(File file) throws FileNotFoundException {
		gameFile = file;
		gameState = DataRead.loadPlayerFile(gameFile);
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
			if(entityComponents.containsKey(Sprite.KEY)) {
				Sprite spriteComponent = (Sprite) entityComponents.get(Sprite.KEY);
				ImageView image = spriteComponent.getImage(); //gets the class of the sprite
				//				image.setX(200);
				//				image.setY(200);
				//image.setImage(new Image("mystery.jpg"));
				System.out.print(image.getX());
				if (entityComponents.containsKey(Position.KEY)) {
					Position p = (Position) entityComponents.get(Position.KEY);
					image.setX(p.getXPos());
					image.setY(p.getYPos());
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
			GI = new GameInitializer(currentLevel); //reinitializes the level.
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
			GI = new GameInitializer(entityMap);
			//gameInitializer = new GameInitializer(intLevelMap.get(0)); //gets the first level map.
		} catch (FileNotFoundException e) {
			System.out.println("You made it this far");
			e.printStackTrace();
		}

		inputHandler = GI.getIH();
		RM = GI.getRM();
		SM = GI.getSM();

		//added code for listening if level should change, not sure this is the best place to put it, but it works
		LS = GI.getC().getCH().getLS();
		LS.getUpdate().addListener((o,oldVal,newVal) -> {
	   //  some action based on the value of newVal like -1 game over, from 1 to 2 change to level two etc. 
	  });

	}
	

	public void execute (double time) {
		SM.execute(time);
	}

	public void render() {
		RM.garbageCollect();
		SM.setActives(RM.renderObjects());
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
