package frontend.components;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
	ArrayList<Tab> tabsList;
	
	public GameEnvironmentView(IDEView v) {
		super(v);
		pane = new TabPane();
		tabsList = new ArrayList<Tab>();
		addTab(); // add the first level
	}
	
	Consumer<List<Tab>> updateTabs = (l) -> {
		for(Tab t : l) {
			t.setText("Level " + (l.indexOf(t)+1));
		}
	};
	
	public void addTab(){
		tabsList.add(new Tab());
		Tab t = tabsList.get(tabsList.size()-1);
		t.setText("Level " + (tabsList.indexOf(t)+1));
		t.setContent(new LevelView());
		t.setOnClosed(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {
				tabsList.remove(t);
				updateTabs.accept(tabsList);
			}
			
		});
		pane.getTabs().add(t);
	}

	public Node getNode() {
		return pane;
	}
}
