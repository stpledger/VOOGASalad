package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import data.DataGameState;
import data.DataRead;
import engine.components.Component;
import engine.components.Sprite;
import engine.setup.GameInitializer;
import engine.setup.RenderManager;
import engine.setup.SystemManager;
import frontend.components.EntityBuilderView;
import frontend.components.Level;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

/**
 * Class that controls how the entity objects are displayed
 * @author Ryan Fu
 *
 */
public class GamePlayerEntityView {
	
	private File gameFile;
	//private Group entityRoot;
	private Map<Level,Map<Integer,Map<String,Component>>> levelMap;
	private DataGameState gameState;
	private GameInitializer gameInitializer;
	public SystemManager systemManager;
	public RenderManager renderManager;
	private Level one;
	private Consumer onMenuLevelSelect;

	
	public GamePlayerEntityView(File file) throws FileNotFoundException {
		gameFile = file;
		gameState = DataRead.loadFile(gameFile);
		levelMap = gameState.getGameState();
		Set<Level> levelMapKeyset = levelMap.keySet();
		one = levelMapKeyset.iterator().next();
		initializeGamePlayerEntityView();
	}

	
	/**
	 * Return a Group that adds all the entity image objects 
	 * @return
	 */
	//Make Entity Group accept a Hashmap for individual Levels
	public Group createEntityGroup() {
		Group entityRoot = new Group();
		Set<Integer> keyset = levelMap.get(one).keySet();  //change dependency later
		Map<String, Component> entityComponents;
		for (int i = 0; i<keyset.size();i++) {
			entityComponents = levelMap.get(one).get(i); //change dependency later ****
			Sprite spriteComponent = (Sprite) entityComponents.get("Sprite");
			ImageView image = spriteComponent.getImage(); //gets the class of the sprite
			entityRoot.getChildren().add(image);
		}
		return entityRoot;
	}
	
	/**
	 * initialize the Game Initializer to create the systemManager and renderManager.
	 * @throws FileNotFoundException 
	 */
	private void initializeGamePlayerEntityView() throws FileNotFoundException {
		gameInitializer = new GameInitializer(levelMap.get(one)); //gets only level 1
		systemManager = gameInitializer.getSM();
		renderManager = gameInitializer.getRM();
	}
	
	/**
	 * Getter Method for the levelMap
	 * @return Returns Map<Level,Map<Integer,Map<String,Component>>> levelMap
	 */
	public Map<Level,Map<Integer,Map<String,Component>>> getLevelMap(){
		return levelMap;
	}
	
	public SystemManager getSystemManager() {
		return systemManager;
	}
	
	public RenderManager getRenderManager() {
		return renderManager;
	}
	
	
}
