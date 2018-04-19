package authoring.views;

import java.util.function.Consumer;

import authoring.MainApplication;
import authoring.entities.Entity;
import authoring.gamestate.Level;
import authoring.views.properties.LevelPropertiesView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *  
 * @author Hemanth Yakkali((hy115)
 * @author Collin Brown(cdb55)
 *
 */
public class LevelView extends ScrollPane {

	private AnchorPane contentPane;
	private Level level;
	Consumer<MouseEvent> addEntity;
	private Double lastX = null;
	private Double lastY = null;

	public LevelView(Level level, int levelNum, Consumer<MouseEvent> aE) {
		this.getStyleClass().add("level-view");
		this.addEntity = aE;
		this.level = level;
		this.contentPane = new AnchorPane();
		this.contentPane.setMinSize(1000, 600);
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setContent((contentPane));
		this.getStyleClass().add("level-view-wrapper");
		this.setupMouseClick(levelNum);
		this.contentPane.getStyleClass().add("level-content-view");
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
