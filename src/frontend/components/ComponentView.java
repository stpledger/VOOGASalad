package frontend.components;

import java.util.Arrays;

import com.sun.prism.paint.Color;

import frontend.IDEView;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.shape.Rectangle;

public class ComponentView extends ViewComponent{
	private TabPane pane;
	private String[] tabsList = {"Sprites", "Blocks", "Game Objects"};
	public ComponentView(IDEView v) {
		super(v);
		pane = new TabPane();
		pane.setPrefWidth(ideView.getComponentViewWidth());
		pane.getStyleClass().add("component-view");
		addTabs();
	}
	
	private void addTabs() {
		//TODO: Convert this to be reflection of all the components
		for(String k : Arrays.asList(tabsList)) {
			Tab temp = new ComponentTab(k, ideView);
			pane.getTabs().add(temp);
		}
	}
	
	@Override
	public Node getNode() {
		return pane;
	}

}
