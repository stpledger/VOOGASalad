package authoring.views;

import java.util.function.Consumer;

import authoring.entities.Entity;
import authoring.gamestate.Level;
import authoring.views.properties.LevelPropertiesView;
import authoring.grid.Grid;
import authoring.grid.Cell;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

/**
 *  
 * @author Hemanth Yakkali((hy115)
 * @author Collin Brown(cdb55)
 *
 */
public class LevelView extends ScrollPane {

	private Grid content;
	private Level level;
	Consumer<MouseEvent> addEntity;
	boolean drag = false; 
	private HBox toolbar;
	
	public LevelView(Level level, int levelNum, Consumer<MouseEvent> aE) {
		this.getStyleClass().add("level-view");
		this.addEntity = aE;
		this.level = level;
		this.content = new Grid();
		this.content.getStyleClass().add("level-view-content");
		this.toolbar = new HBox(200);
		this.content.getChildren().add(toolbar);
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setContent((content));
		this.setupMouseClick(levelNum);
		this.setupMouseDrag();
		Button button = new Button("Cocks");
		button.setOnAction(e->{
			this.content.addRow();
		});
		toolbar.getChildren().add(button);
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
	 * Sets the onMouseReleased method for the content to handle dragging.
	 */
	private void setupMouseDrag() {
		content.setOnDragDetected((e)->{
			this.drag = true;
		});
	}

	/**
	 * Adds entity to the level view both to be seen graphically and to the specific 
	 * level object
	 * @param e Entity to be added to the LevelView
	 */
	public void addEntity(Entity e) {
		this.content.getChildren().add(e);
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
