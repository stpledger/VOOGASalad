package frontend.components;

import com.sun.prism.paint.Color;

import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
public class Toolbar implements ViewComponent{
	private HBox bar;
	
	public Toolbar() {
		bar = new HBox();
	}
	
	public Node getNode() {
		return bar;
	}
}
