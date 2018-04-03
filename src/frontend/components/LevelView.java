package frontend.components;

import java.util.ArrayList;

import frontend.IDEView;
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
	
}
