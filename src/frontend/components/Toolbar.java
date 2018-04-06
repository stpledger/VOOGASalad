package frontend.components;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Method;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class Toolbar extends ViewComponent{
	private HBox toolbar;
	private ArrayList<Node> toolbarNodes = new ArrayList<Node>();
	
	private String[] fileMenuList = {"create", "load", "save"};
	private String[] editMenuList = {"newSprite", "editSprites"};
	private String[] gameMenuList = {"addLevel", "settings", "play"};
	private String[] toolsMenuList = {"add" , "delete", "edit"};
	
	
	
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
	private Node createMenu(String name, Boolean sticky,  String[] items) {
		ComboBox<String> temp = new ComboBox<String>();
		temp.setPromptText(name);
		temp.getItems().addAll(Arrays.asList(items));
		temp.setOnAction(action -> {
			try {
				if(!temp.getSelectionModel().isEmpty()) {
					broadcast.setMessage(temp.getSelectionModel().getSelectedItem(),null);
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
		cb.getSelectionModel().select((String) tool); 
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
	
	