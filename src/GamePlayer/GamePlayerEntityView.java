package GamePlayer;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data.DataRead;
import data.GameState;
import engine.components.Component;
import engine.components.Sprite;
import engine.setup.GameInitializer;
import engine.setup.RenderManager;
import engine.setup.SystemManager;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Class that controls how the entity objects are displayed
 * @author Ryan
 *
 */
public class GamePlayerEntityView {
	
	private File gameFile;
	//private Group entityRoot;
	private Map<Integer, Map<String, Component>> entityMap;
	private GameState gameState;
	private GameInitializer gameInitializer;
	private SystemManager systemManager;
	private RenderManager renderManager;
	
	public GamePlayerEntityView(File file) {
		gameFile = file;
		gameState = DataRead.loadFile(gameFile);
		entityMap = gameState.getGameState();
		initializeGamePlayerEntityView();
	}
	
	/**
	 * Return a Group that adds all the entity image objects 
	 * @return
	 */
	public Group createEntityGroup() {
		Group entityRoot = new Group();
		Set<Integer> keyset = entityMap.keySet();
		Map<String, Component> entityComponents;
		for (int i = 0; i<keyset.size();i++) {
			entityComponents = entityMap.get(i);
			Sprite spriteComponent = (Sprite) entityComponents.get("Sprite");
			ImageView image = spriteComponent.getImage(); //gets the class of the sprite
			entityRoot.getChildren().add(image);
		}
		return entityRoot;
	}
	
	/**
	 * initialize the Game Initializer to create the systemManager and renderManager.
	 */
	private void initializeGamePlayerEntityView() {
		gameInitializer = new GameInitializer(entityMap);
		systemManager = gameInitializer.getSM();
		renderManager = gameInitializer.getRM();
	}

	
	
}
