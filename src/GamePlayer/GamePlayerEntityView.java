package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import data.DataGameState;
import data.DataRead;
import engine.components.Component;
import engine.components.Dimension;
import engine.components.Position;
import engine.components.Sprite;
import engine.setup.GameInitializer;
import engine.systems.InputHandler;
import frontend.components.Level;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


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

	public GamePlayerEntityView(File file) throws FileNotFoundException {
		gameFile = file;
		gameState = DataRead.loadPlayerFile(gameFile);
		levelMap = gameState.getGameState();

		levelEntityMap = createEntityGroupMap(levelMap);

		for(Level level : levelMap.keySet()) {
			entityMap = levelMap.get(level);
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
			if(entityComponents.containsKey("Sprite")) {
				Sprite spriteComponent = (Sprite) entityComponents.get("Sprite");
				ImageView image = spriteComponent.getImage(); //gets the class of the sprite
				//				image.setX(200);
				//				image.setY(200);
				//image.setImage(new Image("mystery.jpg"));
				System.out.print(image.getX());
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
	private void initializeGamePlayerEntityView() {
		try {
			gameInitializer = new GameInitializer(entityMap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("You made it this far");
			e.printStackTrace();
		}
		inputHandler = gameInitializer.getIH();
	}

	public void execute (double time) {
		gameInitializer.execute(time);
	}

	public void render() {
		gameInitializer.render();
	}

	public void setInput(KeyCode code){
		inputHandler.addCode(code);
	}

	public void removeInput (KeyCode code) {
		inputHandler.removeCode(code);
	}

}
