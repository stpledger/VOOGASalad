package frontend.components;

import frontend.IDEView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class GameEnvironmentView extends ViewComponent {
	TabPane pane;
	Button addButton;
	
	public GameEnvironmentView(IDEView v) {
		super(v);
		pane = new TabPane();
		addTab("test");
	}
	
	public void addTab(String name){
		Tab t = new Tab(name);
		pane.getTabs().add(t);
	}
	
	public Node getNode() {
		return pane;
	}
}
