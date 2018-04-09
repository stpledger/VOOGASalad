package frontend.components;

import javafx.event.EventHandler;
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
	
	private GridPane pane;
	private Level level;
	
	public LevelView(Level level, int levelNum, Broadcast broadcast) {
		//Create the Grid Pane
		pane = new GridPane();
		pane.getStyleClass().add("level-view");
				
		this.level = level;
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
					LevelPropertiesView lView = new LevelPropertiesView(levelNum, level.getPropertyList());
					lView.open();
				}
			}
			
		});
	}	
	
}
