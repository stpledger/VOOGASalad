package frontend.components;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class Toolbar extends ViewComponent {
	private HBox toolbar;
	
	private ArrayList<Node> toolbarNodes = new ArrayList<Node>();

	
	//Initialize the menus in Hashmaps<String placeHolder, methodName>
	TreeMap<String,String> fileMenuList = new TreeMap<String,String>(){{
		put("Create","create");
		put("Load", "load");
		put("Save", "save");
	}}; 
	TreeMap<String,String> gameMenuList = new TreeMap<String,String>() {{
		put("Add Level", "addLevel");
		put("Settings", "settings");
		put("Play", "play");
	}};
	TreeMap<String, String> entityMenuList = new TreeMap<String,String>(){{
		//put("Edit Entity", "editEntity");
		put("New Entity", "createEntity");
		//put("Delete Entity", "deleteEntity");
	}};
	TreeMap<String,String> toolsMenuList = new TreeMap<String,String>() {{
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
		toAdd.add(createMenu("Entity", false, entityMenuList));
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
	private Node createMenu(String name, Boolean sticky,  Map<String,String> items) {
		ComboBox<String> temp = new ComboBox<String>();
		temp.setPromptText(name);
		temp.getItems().addAll(items.keySet());
		temp.setOnAction(action -> {
			try {
					if(!temp.getSelectionModel().getSelectedItem().equals(null)) {
						broadcast.setMessage(items.get(temp.getSelectionModel().getSelectedItem()),null);
						if(!sticky) {
							temp.getSelectionModel().select(null);//TODO: This throws and indexoutofbounds error but runs fine because it makes a null value
						}
					}
			} catch (Exception error) {
				System.out.println("Error trying to process toolbar selection: " + temp.getSelectionModel().getSelectedItem());
			}
			});
		temp.getStyleClass().add("combo-box");
		toolbarNodes.add(temp);
		return temp;
	}
	/**
	 * Method that can be called to set the active tool
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
}
	
	