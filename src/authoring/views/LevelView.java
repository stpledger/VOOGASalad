package authoring.views;

import java.util.function.Consumer;

import authoring.entities.Entity;
import authoring.factories.ElementFactory;
import authoring.factories.ElementType;
import authoring.gamestate.Level;
import authoring.views.properties.LevelPropertiesView;
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
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount()==2) {
				LevelPropertiesView lView = new LevelPropertiesView(level, levelNum);
				lView.open();
			} else if(e.getButton().equals(MouseButton.SECONDARY)) {
				ContextMenu cMenu = new ContextMenu();
				try {
					MenuItem addCol = (MenuItem) eFactory.buildElement(ElementType.MenuItem, "Add Column");
					MenuItem addRow = (MenuItem) eFactory.buildElement(ElementType.MenuItem, "Add Row");
					MenuItem addFiveCol = (MenuItem) eFactory.buildElement(ElementType.MenuItem, "Add 5 Columns");
					MenuItem addFiveRow = (MenuItem) eFactory.buildElement(ElementType.MenuItem, "Add 5 Rows");
					MenuItem cancel = (MenuItem) eFactory.buildElement(ElementType.MenuItem, "Cancel");
					addCol.setOnAction(e1->{
						this.content.addCol(ADD_ONE);
					});
					addRow.setOnAction(e1->{
						this.content.addRow(ADD_ONE);
					});
					addFiveCol.setOnAction(e1->{
						this.content.addCol(ADD_FIVE);
					});
					addFiveRow.setOnAction(e1->{
						this.content.addRow(ADD_FIVE);
					});
					cancel.setOnAction(e1->{
						cMenu.hide();
					});
					cMenu.getItems().addAll(addCol,addRow,addFiveCol,addFiveRow,cancel);
				} catch (Exception e1) {
					e1.printStackTrace();
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
