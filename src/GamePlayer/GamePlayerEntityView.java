package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data.DataGameState;
import data.DataRead;
import data.GameState;
import engine.components.Component;
import engine.components.Sprite;
import engine.setup.GameInitializer;
import engine.setup.RenderManager;
import engine.setup.SystemManager;
import frontend.components.Level;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
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
	private DataGameState gameState;
	private GameInitializer gameInitializer;
	public SystemManager systemManager;
	public RenderManager renderManager;
	private Level one;
	
	public GamePlayerEntityView(File file) {
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
	
	public SystemManager getSystemManager() {
		return systemManager;
	}
	
	public RenderManager getRenderManager() {
		return renderManager;
	}
	
	
}
