package frontend.components;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.lang.reflect.Method;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class Toolbar extends ViewComponent {
	private HBox toolbar;
	private ArrayList<Node> toolbarNodes = new ArrayList<Node>();
	
	HashMap<String,String> fileMenuList = new HashMap<String,String>(){{
		put("Create","create");
		put("Load", "load");
		put("Save", "save");
	}}; 
	HashMap<String,String> gameMenuList = new HashMap<String,String>() {{
		put("Add Level", "addLevel");
		put("Settings", "settings");
		put("Play", "Play");
	}};
	HashMap<String,String> toolsMenuList = new HashMap<String,String>() {{
		put("Add", "addTool");
		put("Delete", "deleteTool");
		put("Edit", "editTool");
	}};
	
	
	
	public Toolbar() {
		super();
		toolbar = new HBox();
		addComponents();
		toolbar.getStyleClass().add("toolbar");
	}
	
	public Node getNode() {
		return toolbar;
	}
	
	/**
	 * Adds necessary toolbars
	 */
	private void addComponents() {
		List<Node> toAdd = new ArrayList<>();
		toAdd.add(createMenu("File", false, fileMenuList));
		// toAdd.add(createMenu("Edit", false, editMenuList));
		toAdd.add(createMenu("Game", false, gameMenuList));
		toAdd.add(createMenu("Tool",true, toolsMenuList));
		toolbar.getChildren().addAll(toAdd);
		
	}

	/**
	 * Dynamically create a menu
	 * @param name the name of the menu
	 * @param items the items in a given menu
	 * @return a Node with the menu
	 */
	private Node createMenu(String name, Boolean sticky,  HashMap<String,String> items) {
		ComboBox<String> temp = new ComboBox<String>();
		temp.setPromptText(name);
		temp.getItems().addAll(items.keySet());
		temp.setOnAction(action -> {
			try {
				if(!temp.getSelectionModel().isEmpty()) {
					broadcast.setMessage(items.get(temp.getSelectionModel().getSelectedItem()),null);
					if(!sticky) {
						temp.getSelectionModel().clearSelection(); //TODO: This throws and indexoutofbounds error but runs fine because it makes a null value
					}
					}
			} catch (Exception error) {
				//TODO: make better error handling
			}
			});
		temp.getStyleClass().add("combo-box");
		toolbarNodes.add(temp);
		return temp;
	}
	/**
	 * 
	 * @param tool
	 */
	public void setTool(Object tool) {
		ComboBox<String> cb = (ComboBox<String>) toolbarNodes.get(2);
		for (Entry<String, String> k : toolsMenuList.entrySet()) {
	        if (k.getValue().equals(tool)) {
	        	cb.getSelectionModel().select(k.getKey());
	        }
		}
	}
	/**
	 * Builds the Broadcast object that will send messages to the controller
	 */
	@Override
	protected Broadcast buildBroadcast() {
		Broadcast b = new Broadcast();
		return b;
	}
}
	
	