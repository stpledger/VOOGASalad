package authoring.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import authoring.entities.Entity;
import authoring.entities.data.EntityLoader;
import authoring.gamestate.Level;
import authoring.views.properties.LocalPropertiesView;
import authoring.views.properties.PropertiesView;
import engine.components.Component;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

/**
 * A container class for all of the cells in a grid, which each represent entities if they are filled.
 * @author Hemanth Yakkali(hy115)
 * @author Dylan Powers
 *
 */
public class Grid extends GridPane {

	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 600;
	
	private final String DEFAULT_STYLE = "-fx-background-color: rgba(0, 0, 0, 0); -fx-border-color: black";
	private final String DRAGGED_OVER_STYLE = "-fx-background-color: #1CFEBA";

	private int numRows;
	private int numCols;
	private List<List<Cell>> cells;
	private int numberOfCells = 0;
	private Level level;
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Initializes the grid with a given number of rows and columns
	 * @param width the desired width of the grid
	 * @param height the desired height of the grid
	 */
	public Grid(int width, int height, Level level) {
		this.numRows = height/Entity.ENTITY_HEIGHT;
		this.numCols = width/Entity.ENTITY_WIDTH;
		this.cells = new ArrayList<>();
		this.level = level;
		for (int i = 0; i < this.numRows; i++) {
			cells.add(new ArrayList<>());
        		for (int j = 0; j < this.numCols; j++) {
            		Cell c = new Cell(numberOfCells++);
            		setupEntityDrop(c);
            		setupLPVOpen(c);
            		cells.get(i).add(c);
            		this.add(c, j, i);
            	}
		}
		this.setPrefSize(width, height);
	}
	
	/**
	 * Empty constructor, use default values
	 */
	public Grid(Level level) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, level);
	}
	
	/**
	 * Sets up the drag and drop utility used to add entities to the grid.
	 * @param c Cell to add the mouse click event listener to
	 */
	private void setupEntityDrop(Cell c) {
		c.setOnDragEntered(e -> c.setStyle(DRAGGED_OVER_STYLE));
		c.setOnDragExited(e -> c.setStyle(DEFAULT_STYLE));
		c.setOnDragOver(e -> {
			if (!e.getGestureSource().equals(c) && e.getDragboard().hasString()) {
				e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			e.consume();
		});
		c.setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();
			EntityLoader el = new EntityLoader();
			ImageView img = new ImageView(db.getImage());
			img.setFitWidth(Entity.ENTITY_WIDTH);
			img.setFitHeight(Entity.ENTITY_HEIGHT);
			img.setOnMouseClicked(e1->{
				if(e1.getClickCount()==2) {
					ContextMenu cMenu = createMenu(c, img);
					cMenu.show(this, e1.getScreenX(), e1.getScreenY());
					cMenu.setAutoHide(true);
				}
			});
			c.getChildren().add(img);
			try {
				Entity en = el.buildEntity(c.getNumber(), db.getString());
				c.setEntity(en);
				level.addEntity(en);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				LOGGER.log(java.util.logging.Level.SEVERE, e1.toString(), e1);
			}
			e.setDropCompleted(true);
			e.consume();
		});
	}
	
	//TODO use element factory and language properties
	private ContextMenu createMenu(Cell c, ImageView img) {
		ContextMenu cMenu = new ContextMenu();
		MenuItem removeEntity = new MenuItem("Remove Entity");
		removeEntity.setOnAction(e-> this.clearCell(c));
		cMenu.getItems().add(removeEntity);
		return cMenu;
	}
	
	private void clearCell(Cell c) {
		level.removeEntity(c.getEntity());
		c.getChildren().clear();
	}

	/**
	 * Sets up mouse click listener which when triggered, opens the local properties view.
	 * @param c Cell to add the mouse click event listener to
	 */
	private void setupLPVOpen(Cell c) {
		c.setOnMouseClicked(e1 -> {
			if (e1.getButton().equals(MouseButton.SECONDARY)) {
				if (c.containsEntity()) {
					PropertiesView pv = new LocalPropertiesView(c.getEntity(), componentList ->  {
						for (Component comp : componentList) {
							c.getEntity().add(comp);
						}
					});
					pv.open();
				}
			}
		});
	}
	
	/**
	 * Adds new row of cells to the grid
	 * @param numTimes Number of rows to add
	 */
	public void addRow(int numTimes) {
		for(int j = 0; j < numTimes; j++) {
			this.cells.add(new ArrayList<>());
			for (int i = 0; i < this.numCols; i++) {
				Cell c = new Cell(numberOfCells++);
				this.cells.get(this.numRows).add(c);
				this.add(c, i, this.numRows);
			}
			this.setPrefHeight(this.getPrefHeight() + Entity.ENTITY_HEIGHT);
			this.numRows++;
		}
	}

	/**
	 * Adds a number of new columns to the grid
	 * @param numTimes Number of columns to add 
	 */
	public void addCol(int numTimes) {
		for(int j = 0; j < numTimes; j++) {
			for(int i = 0; i < this.numRows; i++) {
				Cell c = new Cell(numberOfCells++);
				this.cells.get(i).add(c);
				this.add(c, this.numCols, i);
			}
			this.setPrefWidth(this.getPrefWidth() + Entity.ENTITY_WIDTH);
			this.numCols++;
		}
	}

}
