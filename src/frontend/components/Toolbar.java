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
	private String[] gameMenuList = {"addLevel", "settings", "play"};
	
	
	
	public Toolbar() {
		super();
		toolbar = new HBox();
		addComponents();
		toolbar.getStyleClass().add("toolbar");
	}
	
	public Node getNode() {
		return toolbar;
	}
	
	private void addComponents() {
		List<Node> toAdd = new ArrayList<>();
		toAdd.add(createMenu("File", fileMenuList));
		toAdd.add(createMenu("Game", gameMenuList));
		toolbar.getChildren().addAll(toAdd);
		
	}

	
	private Node createMenu(String name, String[] items) {
		ComboBox<String> temp = new ComboBox<String>();
		temp.setPromptText(name);
		temp.getItems().addAll(Arrays.asList(items));
		temp.setOnAction(action -> {
			try {
				if(!temp.getSelectionModel().isEmpty()) {
					broadcast.setMessage(temp.getSelectionModel().getSelectedItem(),null);
					temp.getSelectionModel().clearSelection(); //TODO: This throws and indexoutofbounds error but runs fine because it makes a null value
				}
			} catch (Exception error) {
				//TODO: make better error handling
				error.printStackTrace();
			}
			});
		temp.getStyleClass().add("combo-box");
		return temp;
	}

	@Override
	protected Broadcast buildBroadcast() {
		Broadcast b = new Broadcast();
		return b;
	}
}
	
	