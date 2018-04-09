package frontend.components;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.sun.glass.ui.Cursor;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class GameEnvironmentView extends TabPane {
	private ArrayList<Tab> tabsList;
	private Object clipboard;
	private String activeTool;
	
	/**
	 * Default Constructor
	 */
	public GameEnvironmentView() {
		super();
		activeTool = "move";
		tabsList = new ArrayList<Tab>();
		addLevel(); // add the first level
	}
	/**
	 * called whenever there is any change to the tabslist
	 * TODO: This might not even need to be a lambda but gotta flex for Duval.
	 */
	Consumer<List<Tab>> updateTabs = (l) -> {
		for(Tab t : l) {
			t.setText("Level " + (l.indexOf(t)+1));
		}
	};
	
	/**
	 * Creates a new LevelView
	 */
	public void addLevel(){
		tabsList.add(new Tab());
		Tab t = tabsList.get(tabsList.size()-1);
		t.setText("Level " + (tabsList.indexOf(t)+1));
		Level level = new Level(tabsList.indexOf(t)+1);
		t.setContent(new LevelView(level,tabsList.indexOf(t)+1));
		t.setOnClosed(new EventHandler<Event>() { //Handles tab closed events
			@Override
			public void handle(Event e) {
				tabsList.remove(t);
				updateTabs.accept(tabsList);
			}
		});
		this.getTabs().add(t);
	}
	
	/**
	 * Set the element in the clipboard
	 * @param o
	 */
	public void setClipboard(Object o) {
		//TODO: add argument check because this is being called from the controller
		clipboard = o;
	}
	
	//TODO: change these class names
	public void addTool() {setTool("add");}
	public void deleteTool() {setTool("delete");}
	public void editTool() {setTool("edit");}
	
	public void setTool(Object o) {
		activeTool = o.toString();
		switch(o.toString()) {
		case "edit":
			this.setCursor(javafx.scene.Cursor.OPEN_HAND);
			break;
		case "add":
		case "delete":
			this.setCursor(javafx.scene.Cursor.HAND);
			break;
		}
	}

}
