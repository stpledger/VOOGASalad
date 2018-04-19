package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import authoring.gamestate.Level;
import data.DataGameState;
import data.DataRead;
import engine.components.*;
import engine.components.groups.Dimension;
import engine.components.groups.Position;
import engine.setup.GameInitializer;
import engine.setup.RenderManager;
import engine.setup.SystemManager;
import engine.systems.InputHandler;
import engine.systems.collisions.LevelStatus;
import javafx.scene.Group;
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
    private LevelStatus LS;

	public GamePlayerEntityView(File file) throws FileNotFoundException {
		gameFile = file;
		gameState = DataRead.loadPlayerFile(gameFile);
		levelMap = gameState.getGameState();

		levelEntityMap = createEntityGroupMap(levelMap);

		for (Level l : levelMap.keySet()) {
			entityMap = levelMap.get(l);
			break;
		}
		//method tht builds the entire levelEntityMap;
	//	levelEntityMap = createEntityGroupMap(levelMap);
		//This is mainly for debugging purposes not entirely sure how you will get specific levels out of the mao
		// because they arent ordered probably will have to iterate through levels and look at levelnum of each
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
			count++;
		}

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
	 * Return a Group that adds all the entity image objects 
	 * @return
	 */
	//Make Entity Group accept a Hashmap for individual Levels
	public Group createEntityGroup() {

		Group entityRoot = new Group();
		Map<String, Component> entityComponents;
		//Changed enclosed code to only load sprites for 

		for(Integer i : entityMap.keySet()) {
			entityComponents = entityMap.get(i);

			if(entityComponents.containsKey(Sprite.KEY)) {
				Sprite spriteComponent = (Sprite) entityComponents.get(Sprite.KEY);
				ImageView image = spriteComponent.getImage(); //gets the class of the sprite

				if (entityComponents.containsKey(Position.KEY)) {
					Position p = (Position) entityComponents.get(Position.KEY);
					image.setX(p.getXPos());
					image.setY(p.getYPos());
				}

				if (entityComponents.containsKey(Dimension.KEY)) {
					Dimension d = (Dimension) entityComponents.get(Dimension.KEY);
					image.setFitHeight(d.getHeight());
					image.setFitWidth(d.getWidth());
				}

				entityRoot.getChildren().add(image);
			}
		}
		return entityRoot;
	}

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

		//added code for listening if level should change, not sure this is the best place to put it, but it works
		LS = GI.getC().getCH().getLS();
		LS.getUpdate().addListener((o,oldVal,newVal) -> {
	   //  some action based on the value of newVal like -1 game over, from 1 to 2 change to level two etc. 
	  });

	}
	

	public void execute (double time) {
		GI.getSM().execute(time);
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
