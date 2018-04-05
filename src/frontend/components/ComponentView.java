package frontend.components;

import java.util.Arrays;

import com.sun.prism.paint.Color;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.shape.Rectangle;

public class ComponentView extends ViewComponent{
	private double componentViewWidth = 300;
	private TabPane pane;
	private String[] tabsList = {"Sprites", "Blocks", "Game Objects"};
	public ComponentView() {
		super();
		pane = new TabPane();
		pane.setPrefWidth(componentViewWidth);
		pane.getStyleClass().add("component-view");
		addTabs();
	}
	
	private void addTabs() {
		//TODO: Convert this to be reflection of all the components
		for(String k : Arrays.asList(tabsList)) {
			Tab temp = new ComponentTab(k, componentViewWidth);
			pane.getTabs().add(temp);
		}
	}
	
	@Override
	public Node getNode() {
		return pane;
	}

	@Override
	protected Broadcast buildBroadcast() {
		Broadcast b = new Broadcast();
		return b;
	}

}
