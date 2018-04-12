package Menu;

import java.util.Map;
import java.util.Set;

import GamePlayer.GamePlayerController;
import GamePlayer.GamePlayerEntityView;
import engine.components.Component;
import frontend.components.Level;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Class for Selecting the corresponding level
 * @author Ryan
 *
 */
public class LevelSelector extends Menu {
	private final String MENU_TITLE = "Level";
	private Map<Integer, Map<Integer,Map<String,Component>>> orderedLevelMap;
	private GamePlayerController mainController;
	private  Map<Integer, Group> levelEntityGroupMap;
	private int levelCount;
	
	public LevelSelector(GamePlayerController g) {
		mainController = g;
		levelEntityGroupMap = g.getGameLevelRoot();
		this.setText(MENU_TITLE);
		this.getItems().addAll(new MenuItem("level1"));
		//createLevelMenu();
	}
	
	/**
	 * Method that creates each MenuItem for a given file;
	 * @param levelMap
	 */
	public void createLevelMenu() {
		Set<Integer> levelKeySet = levelEntityGroupMap.keySet();
		int count = levelKeySet.size();
		levelCount = 1;
		for (int i = 1; i<=count; i++) {
			//Adds each level to the LevelList
			MenuItem currentMenu = new MenuItem("Level" + levelCount);
			currentMenu.setOnAction(new EventHandler<ActionEvent>() { //event listener when the menu is selected.
			    public void handle(ActionEvent t) {
			    		mainController.changeGameLevel(levelEntityGroupMap.get(levelCount));
			    }
			});
			this.getItems().add(currentMenu);
			levelCount++;
		}
	}
	

}
