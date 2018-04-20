package authoring.grid;

import authoring.entities.Entity;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;

/**
 * A container class for all of the cells in a grid, which each represent entities if they are filled.
 * @author Dylan Powers
 *
 */
public class Grid extends TilePane {

	private Cell[][] _grid;
	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 600;
	/**
	 * Initializes the grid with a given number of rows and columns
	 * @param width the desired width of the grid
	 * @param height the desired height of the grid
	 */
	public Grid(int width, int height) {
		int numRows = width/Entity.ENTITY_WIDTH;
		int numCols = height/Entity.ENTITY_HEIGHT;
		_grid = new Cell[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				Cell c = new Cell();
				_grid[i][j] = c;
				this.getChildren().add(c);
			}
		}
		this.setPrefSize(width, height);
		this.setPrefTileWidth(Entity.ENTITY_WIDTH);
		this.setPrefTileHeight(Entity.ENTITY_HEIGHT);
	}
	
	/**
	 * Empty constructor, use default values
	 */
	public Grid() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	

}
