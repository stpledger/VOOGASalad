package GamePlayer;

import java.util.Map;
import java.util.Set;

import engine.components.Component;
import frontend.components.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * ComboBox Class that allows user to select the corresponding level
 * @author Ryan
 *
 */
public class LevelSelector extends ComboBox{
	
	private int size;
	private ObservableList<Map<Integer, Map<String, Component>>> levelList;

	public LevelSelector(Map<Level,Map<Integer,Map<String,Component>>> levelMap) {
				Set<Level> levelKeySet = levelMap.keySet();
				size = levelKeySet.size();
				levelList = createLevelList(levelMap); //levelList now is a list of each level as a combobox.
				
	}
	
	private ObservableList<Map<Integer, Map<String, Component>>> createLevelList(Map<Level,Map<Integer,Map<String,Component>>> levelMap) {
		ObservableList<Map<Integer, Map<String, Component>>> levelList = FXCollections.observableArrayList();
		Set<Level> levelKeySet = levelMap.keySet();
		while (levelKeySet.iterator().hasNext()) {
			//Adds each level to the LevelList
			levelList.add(levelMap.get(levelKeySet.iterator().next()));
		}
		return levelList;
	}
	
	/**
	 * Listener to select the correct map
	 */
	private void selectFileListener() {
		
	}
}
