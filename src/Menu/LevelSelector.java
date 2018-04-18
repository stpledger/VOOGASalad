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
	private GamePlayerController mainController;
	private  Map<Integer, Group> levelEntityGroupMap;
	private int levelCount;
	
	public LevelSelector(GamePlayerController g) {
		mainController = g;
		levelEntityGroupMap = g.getGameLevelRoot();
		System.out.println(levelEntityGroupMap);
		this.setText(MENU_TITLE);
		createLevelMenu();
	}
	
	/**
	 * Method that creates each MenuItem for a given file;
	 * @param
	 */
	public void createLevelMenu() {
		Set<Integer> levelKeySet = levelEntityGroupMap.keySet();
		int count = levelKeySet.size();
		levelCount = 1;
		for (int i = 1; i<=count; i++) {
			//Adds each level to the LevelList
			MenuItem currentMenu = new MenuItem("Level " + levelCount);
			currentMenu.setOnAction(new EventHandler<ActionEvent>() { //event listener when the menu is selected.
			    public void handle(ActionEvent t) {
			    		int level = obtainLevelInteger(currentMenu.getText());
			    		mainController.changeGameLevel(level);
			    }
			});
			this.getItems().add(currentMenu);
			levelCount++;
		}
	}
	
	/**
	 * Helper method that converts a 2-word string "level + int" and obtains just the level
	 * @param s
	 * @return
	 */
	private int obtainLevelInteger(String s) {
		String[] stringArray = new String[2];
		stringArray = s.split("\\s+"); //splits by whitespace
		int num = Integer.parseInt(stringArray[1]);
		return num;
	}

}
