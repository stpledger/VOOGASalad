package frontend.components;

import java.util.function.Consumer;

import frontend.entities.Entity;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
/**
 *  
 * @author Hemanth Yakkali((hy115)
 * @author Collin Brown(cdb55)
 *
 */
public class LevelView extends BorderPane {
	
	private ScrollPane pane;
	private AnchorPane contentPane;
	private Level level;
	Consumer<MouseEvent> addEntity;
	
	public LevelView(Level level, int levelNum, Consumer<MouseEvent> aE) {
		this.getStyleClass().add("level-view");
		this.addEntity = aE;
		this.level = level;
		this.pane = new ScrollPane();
		this.contentPane = new AnchorPane();
		this.contentPane.setMinSize(600, 600);
		this.pane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		this.pane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.pane.setContent((contentPane));
		this.setTop(toolbar);
		this.setCenter((pane));
		this.getStyleClass().add("level-view-wrapper");
		this.setupMouseClick(levelNum);
	}
	
	/**
	 * Sets up mouse click events
	 * @param levelNum Level number
	 */
	private void setupMouseClick(int levelNum) {
		this.setOnMouseClicked(e-> {		
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount()==2) {
				LevelPropertiesView lView = new LevelPropertiesView(level, levelNum);
				lView.open();
			}
		});
	}
	
	/**
<<<<<<< HEAD
=======
	 * Sets up buttons for each level view
	 */
	private void setupButtons() {
		IButton button = new PropertiesButton();
		
		ElementFactory factory = new ElementFactory();
		this.HUDButton = factory.makeElement("Jesus");
//		this.HUDButton = button.makeButton("HUD Properties", e->{
//			HUDPropertiesView HPV = new HUDPropertiesView(level);
//			HPV.open();
//		});
//		this.GButton = button.makeButton("Global Properties", e->{
//			GlobalPropertiesView GPV = new GlobalPropertiesView(level);
//			GPV.open();
//		});
		toolbar.getChildren().addAll(this.GButton,this.HUDButton);
	}
	
	/**
>>>>>>> 154ca4ff43e14ca9e7cd8beb5b990c529b8366c7
	 * Adds entity to the level view both to be seen graphically and to the specific 
	 * level object
	 * @param e Entity to be added to the LevelView
	 */
	public void addEntity(Entity e) {
		this.contentPane.getChildren().add(e);
		level.addEntity(e);
	}
	
	/**
	 * Retrieves the level attributed to this levelView
	 * @return the level in this levelView
	 */
	public Level getLevel() {
		return this.level;
	}
}
