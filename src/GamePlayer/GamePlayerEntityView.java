package GamePlayer;

import java.io.File;

import data.DataRead;
import data.GameState;
import engine.setup.GameInitializer;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * Class that controls how the entity objects are displayed
 * @author Ryan
 *
 */
public class GamePlayerEntityView {
	
	private File gameFile;
	private Group entityRoot;
	
	public GamePlayerEntityView(File file) {
		gameFile = file;
		
	}
	
	GameState gameState = DataRead.loadFile(gameFile);
	GameInitializer gameInitializer = new GameInitializer(null, 0, 0);
		
	
	
	private void initializeGamePlayerView() {
		
		
	}

	
	
}
