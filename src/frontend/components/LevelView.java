package frontend.components;

import frontend.entities.Entity;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
/**
 *  
 * @author Hemanth Yakkali((hy115)
 * @author Collin Brown(cdb55)
 *
 */
public class LevelView extends ScrollPane {
	
	private BorderPane pane;
	private AnchorPane contentPane;
	private HBox toolbar;
	private Level level;
	private Button HUDButton;
	private Button GButton;
	
	public LevelView(Level level, int levelNum) {
		this.getStyleClass().add("level-view");
		this.level = level;
		this.pane = new BorderPane();
		this.toolbar = new HBox();
		this.contentPane = new AnchorPane();
		this.pane.setCenter(contentPane);
		this.setContent(pane);
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.getStyleClass().add("level-view-wrapper");
		this.setupMouseClick(levelNum);
		this.setupButtons();
	}
	
	/**
	 * Sets up mouse click events
	 * @param levelNum Level number
	 */
	private void setupMouseClick(int levelNum) {
		this.setOnMouseClicked(e-> {		
			if(e.getButton().equals(MouseButton.SECONDARY)) {
				LevelPropertiesView lView = new LevelPropertiesView(level, levelNum);
				lView.open();
			}
		});
	}
	
	/**
	 * Sets up buttons for each level view
	 */
	private void setupButtons() {
		this.HUDButton = MenuItemBuilder.buildButton("HUD Properties", e->{
			HUDPropertiesView HPV = new HUDPropertiesView(level);
			HPV.open();
		});
		this.GButton = MenuItemBuilder.buildButton("Global Properties", e->{
			GlobalPropertiesView GPV = new GlobalPropertiesView(level);
			GPV.open();
		});
		toolbar.getChildren().add(this.GButton);
		toolbar.getChildren().add(this.HUDButton);
		this.pane.setTop(toolbar);
	}
	
	/**
	 * Adds entity to the level view both to be seen graphically and to the specific 
	 * level object
	 * @param e Entity to be added to the LevelView
	 */
	public void addEntity(Entity e) {
		this.contentPane.getChildren().add(e);
		level.addEntity(e);
	}
}
