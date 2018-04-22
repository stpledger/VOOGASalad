package Menu;

import java.util.Map;
import java.util.Set;

import GamePlayer.GamePlayerController;
import authoring.gamestate.Level;
import engine.components.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * ComboBox Class that allows user to select the corresponding level
 * @author Ryan
 *
 */
public class MenuGameBar extends MenuBar{
	
	private int size;
	private ObservableList<Map<Integer, Map<String, Component>>> levelList;
	
	private LevelSelector levelMenu;

	public MenuGameBar(GamePlayerController g) {
				levelMenu = new LevelSelector(g);
				//Set<Level> levelKeySet = levelMap.keySet();
				//size = levelKeySet.size();
				//levelList = createLevelList(levelMap); //levelList now is a list of each level as a combobox.
				this.getMenus().add(levelMenu);
				
	}
	
}
