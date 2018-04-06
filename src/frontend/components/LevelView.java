package frontend.components;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class LevelView extends ScrollPane {
	GridPane pane;
	ArrayList<Node> activeObjects = new ArrayList<Node>();
	public LevelView() {
		//Create the Grid Pane
		pane = new GridPane();
		pane.getStyleClass().add("level-view");
		
		this.setContent(pane);
		this.getStyleClass().add("level-view-wrapper");
		//Always hide the scrollbar
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setVbarPolicy(ScrollBarPolicy.NEVER);
	}
	
	public void addElement(Object n) {
		System.out.println(n);
		//this.getChildren().add(n);
	}

	public void setText(String string) {
		// TODO Auto-generated method stub
		
	}
	
}
