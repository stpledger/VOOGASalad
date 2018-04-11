package Menu;

import java.util.Map;
import java.util.Set;

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
	
	public LevelSelector() {
		this.setText(MENU_TITLE);
		this.getItems().addAll(new MenuItem("Level 1"), new MenuItem("Level 2"), new MenuItem("Level 3"));
	}
	
	/**
	 * Method that creates each MenuItem for a given file;
	 * @param levelMap
	 */
	public void createLevelMenu(Map<Level,Map<Integer,Map<String,Component>>> levelMap) {
		Set<Level> levelKeySet = levelMap.keySet();
		int count = 1;
		while (levelKeySet.iterator().hasNext()) {
			//Adds each level to the LevelList
			MenuItem currentMenu = new MenuItem("Level" + count);
			currentMenu.setOnAction(new EventHandler<ActionEvent>() { //event listener when the menu is selected.
			    public void handle(ActionEvent t) {
//			    		GamePlayerEntityView gameView = new GamePlayerEntityView();
//			        Group gameRoot = gameView.createEntityGroup(levelMap.get(levelKeySet.iterator().next()));  //create a new root 
			    }
			});

		}
	}
	

}
