package frontend.components;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
public class Toolbar implements ViewComponent{
	private HBox bar = new HBox();
	public Toolbar() {
		
	}
	
	public Node getNode() {
		return bar;
	}
}
