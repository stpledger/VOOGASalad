package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import authoring.gamestate.Level;
import data_management.DataGameState;
import data_management.DataRead;
import data_management.DataWrite;
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
	private Map<Level,Map<Integer,Map<String,Component>>> levelMap;
	private Map<Integer, Map<String, Component>> entityMap;
	private Map<Integer, Pane> levelEntityMap;
	private DataGameState gameState;
	private File gameFile;
	private GameInitializer gameInitializer;
	private InputHandler inputHandler;
	private RenderManager renderManager;
	private SystemManager systemManager;
	private Map<Integer, Map<Integer,Map<String,Component>>> intLevelMap;
	private LevelStatus LS;

	private int PlayerKey;

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

	public Pane createIndividualEntityGroup(Map<Integer, Map<String, Component>> entityMap) {
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
				System.out.print(image.getX());
				if (entityComponents.containsKey(Position.KEY)) {
					Position p = (Position) entityComponents.get(Position.KEY);
					image.setX(p.getXPos());
					image.setY(p.getYPos());

					// setting up values to track for window scroll
					if(entityComponents.containsKey(Player.KEY)){
                        PlayerKey = i;
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
			gameInitializer = new GameInitializer(entityMap);
			//gameInitializer = new GameInitializer(intLevelMap.get(0)); //gets the first level map.
		} catch (FileNotFoundException e) {
			System.out.println("You made it this far");
			e.printStackTrace();
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
        Map<String, Component> playerComponents;
        playerComponents = entityMap.get(PlayerKey);
        Position position = (Position) playerComponents.get(Position.KEY);
        gameRoot.setTranslateY((-1 * position.getYPos()) + 250);
        System.out.println(position.getYPos());
        System.out.println(gameRoot.getTranslateY());

    }

}
