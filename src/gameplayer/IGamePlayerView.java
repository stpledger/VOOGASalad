package gameplayer;

import java.util.Map;

import engine.components.Component;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public interface IGamePlayerView {

	public void setActiveLevel(int i);
	
	public void execute (double time);
	
	public void render ();
	
	public void setInput(KeyCode code);
	
	public void removeInput(KeyCode code);
	
	public Map<Integer, Map<String, Component>> getPlayerKeys();
	
	public void saveGame();
	
	public void updateScroll(Pane gameRoot);
	
	
}
