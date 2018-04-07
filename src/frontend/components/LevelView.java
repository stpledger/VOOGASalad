package frontend.components;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
/**
 * 
 * @author Collin Brown(cdb55)
 * @author Hemanth Yakkali((hy115)
 *
 */
public class LevelView extends ScrollPane {
	
	GridPane pane;
	ArrayList<Node> activeObjects = new ArrayList<Node>();
	private String levelText;
	private double levelTime;
	private double levelDistance;
	
	public LevelView() {
		//Create the Grid Pane
		pane = new GridPane();
		pane.getStyleClass().add("level-view");
		
		this.setContent(pane);
		this.getStyleClass().add("level-view-wrapper");
		//Always hide the scrollbar
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.onMouseClickedProperty()
	}
	
	/**
	 * 
	 * @param n Object to be added to the level
	 */
	public void addElement(Object n) {
		System.out.println(n);
		//this.getChildren().add(n);
	}

	/**
	 * 
	 * @param string Name of level to be shown on the tab
	 */
	public void setLevelText(String string) {
		levelText = string;
	}
	
	
	public void setLevelDuration(double time) {
		levelTime = time;
	}
	
	public void setLevelLength(double distance) {
		levelDistance = distance;
	}
	
}
