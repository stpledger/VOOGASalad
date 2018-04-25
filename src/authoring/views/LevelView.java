package authoring.views;

import java.util.function.Consumer;
import java.util.logging.Logger;

import authoring.entities.Entity;
import authoring.factories.ClickElementType;
import authoring.factories.ElementFactory;
import authoring.gamestate.Level;
import authoring.grid.Grid;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
	private ElementFactory eFactory;
	private static final int ADD_FIVE = 5;
	private static final int ADD_ONE = 1;
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public LevelView(Level level, int levelNum, Consumer<MouseEvent> aE) {
		this.getStyleClass().add("level-view");
		this.eFactory = new ElementFactory();
		this.addEntity = aE;
		this.level = level;
		this.content = new Grid();
		this.content.getStyleClass().add("level-view-content");
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setContent((content));
		this.setupMouseClick(levelNum);
		this.setupMouseDrag();
	}

	/**
	 * Sets up mouse click events
	 * @param levelNum Level number
	 */
	private void setupMouseClick(int levelNum) {
		this.setOnMouseClicked(e -> {		
			if(e.getButton().equals(MouseButton.SECONDARY)) {
				ContextMenu cMenu = new ContextMenu();
				try {
					MenuItem addCol = (MenuItem) eFactory.buildClickElement(ClickElementType.MenuItem, "Add Column", e1->this.content.addCol(ADD_ONE));
					MenuItem addRow = (MenuItem) eFactory.buildClickElement(ClickElementType.MenuItem, "Add Row", e1->this.content.addRow(ADD_ONE));
					MenuItem addFiveCol = (MenuItem) eFactory.buildClickElement(ClickElementType.MenuItem, "Add 5 Columns", e1->this.content.addCol(ADD_FIVE));
					MenuItem addFiveRow = (MenuItem) eFactory.buildClickElement(ClickElementType.MenuItem, "Add 5 Rows", e1->this.content.addRow(ADD_FIVE));
					MenuItem cancel = (MenuItem) eFactory.buildClickElement(ClickElementType.MenuItem, "Cancel", e1->cMenu.hide());
					cMenu.getItems().addAll(addCol,addRow,addFiveCol,addFiveRow,cancel);
				} catch (Exception e1) {
					LOGGER.log(java.util.logging.Level.SEVERE, e1.toString(), e1);
				}
				cMenu.show(this, e.getScreenX(), e.getScreenY());
				cMenu.setAutoHide(true);
			}
		});
	}
	/**
	 * Sets the onMouseReleased method for the content to handle dragging.
	 */
	private void setupMouseDrag() {
		content.setOnDragDetected(e -> {
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
