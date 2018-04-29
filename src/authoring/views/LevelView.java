package authoring.views;

import java.util.function.Consumer;

import authoring.entities.Entity;
import authoring.factories.ClickElementType;
import authoring.factories.ElementFactory;
import authoring.factories.ElementType;
import authoring.gamestate.Level;
import authoring.grid.Grid;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

/**
 * @author Hemanth Yakkali((hy115)
 * @author Collin Brown(cdb55)
 */
public class LevelView extends ScrollPane {

	private Grid content;
	private Level level;
	Consumer<MouseEvent> addEntity;
	boolean drag = false; 

	public LevelView(Level level, int levelNum, Consumer<MouseEvent> aE) {
		this.getStyleClass().add("level-view");
		this.addEntity = aE;
		this.level = level;
		this.content = new Grid(level);
		this.content.getStyleClass().add("level-view-content");
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setContent((content));
		this.setupMouseDrag();
	}

	/**
	 * Sets the onMouseReleased method for the content to handle dragging.
	 */
	private void setupMouseDrag() {
		content.setOnDragDetected(e -> this.drag = true);
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
