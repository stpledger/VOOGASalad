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

/**
 * Class that controls how the entity objects are displayed
 * @author Ryan Fu
 *
 */
public class GamePlayerEntityView {

	private Map<Level,Map<Integer,Map<String,Component>>> levelMap;
	private Map<Integer, Map<String, Component>> entityMap;
	private Map<Integer, Group> levelEntityMap;

	private DataGameState gameState;
	private File gameFile;

	private GameInitializer GI;
	private InputHandler inputHandler;
	private RenderManager RM;
	
	public GamePlayerEntityView(File file) throws FileNotFoundException {
		gameFile = file;
		gameState = DataRead.loadFile(gameFile);
		levelMap = gameState.getGameState();

		levelEntityMap = createEntityGroupMap(levelMap);

		for (Level l : levelMap.keySet()) {
			entityMap = levelMap.get(l);
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
	private Group createIndividualEntityGroup(Map<Integer, Map<String, Component>> entityMap) {

		Group entityRoot = new Group();
		Map<String, Component> entityComponents;

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
