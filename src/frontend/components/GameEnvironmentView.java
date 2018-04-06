package frontend.components;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
public class GameEnvironmentView extends ViewComponent {
	private TabPane pane;
	private ArrayList<Tab> tabsList;
	private Object clipboard;
	
	/**
	 * Default Constructor
	 */
	public GameEnvironmentView() {
		super();
		pane = new TabPane();
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
		t.setContent(new LevelView());
		t.setOnClosed(new EventHandler<Event>() { //Handles tab closed events
			@Override
			public void handle(Event e) {
				tabsList.remove(t);
				updateTabs.accept(tabsList);
			}
		});
		pane.getTabs().add(t);
	}
	
	/**
	 * Set the element in the clipboard
	 * @param o
	 */
	public void setClipboard(Object o) {
		//TODO: add argument check because this is being called from the controller
		clipboard = o;
		((LevelView) tabsList.get(0).getContent()).addElement(o);
	}
	
	/**
	 * Get the graphic representation of GameEnvironmentView
	 */
	public Node getNode() {
		return pane;
	}
	/**
	 * Build the Broadcast object to communicate with the controller
	 */
	@Override
	protected Broadcast buildBroadcast() {
		Broadcast b = new Broadcast();
		return b;
	}
}
