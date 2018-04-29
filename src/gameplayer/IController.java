package GamePlayer;

import java.io.FileNotFoundException;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface IController {
	
	public Scene initializeStartScene();
	
	public void initializeGameStart() throws FileNotFoundException;
	
	public void initializeGameAnimation();
	
	public Map<Integer, Pane> getGameLevelRoot();
	
	public void changeGameLevel(int level);
	
	public void setHighScoreView();
	
	public void restartGame();
	
	public void saveGame();
	
}
