package frontend.components;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
	private int levelNumber;
	private String levelText;
	private double levelTime;
	private double levelDistance;
	
	public LevelView(int level, Broadcast broadcast) {
		//Create the Grid Pane
		pane = new GridPane();
		pane.getStyleClass().add("level-view");
		
		this.setLevelNum(level);
		this.setContent(pane);
		this.getStyleClass().add("level-view-wrapper");
		//Always hide the scrollbar
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				MouseButton button = event.getButton();
				if(button == MouseButton.SECONDARY) {
					LevelPropertiesView lView = new LevelPropertiesView(levelNumber, broadcast);
					lView.open(new ArrayList<String>(Arrays.asList("Time","Distance")));
				}
			}
			
		});
	}
	
	private void setLevelNum(int level) {
		levelNumber = level;
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
