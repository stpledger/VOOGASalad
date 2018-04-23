package authoring.grid;

import authoring.entities.Entity;
import javafx.scene.layout.TilePane;

/**
 * A container class for all of the cells in a grid, which each represent entities if they are filled.
 * @author Dylan Powers
 * @author Hemanth Yakkali(hy115)
 *
 */
public class Grid extends TilePane {

	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 600;
	
	private int numRows;
	private int numCols;
	
	/**
	 * Initializes the grid with a given number of rows and columns
	 * @param width the desired width of the grid
	 * @param height the desired height of the grid
	 */
	public Grid(int width, int height) {
		this.numRows = height/Entity.ENTITY_HEIGHT;
		this.numCols = width/Entity.ENTITY_WIDTH;
		
		for(int i=0;i<this.numCols*this.numRows;i++) {
			Cell c = new Cell();
			this.getChildren().add(c);
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

	/**
	 * Adds new row of cells to the grid
	 * @param numTimes Number of rows to add
	 */
	public void addRow(int numTimes) {
		for(int j=0;j<numTimes;j++) {
			for(int i=0;i<this.numCols;i++) {
				Cell c = new Cell();
				this.getChildren().add(c);
			}
			this.setPrefHeight(this.getPrefHeight()+Entity.ENTITY_HEIGHT);
			this.numRows++;
		}
	}
	
	/**
	 * Adds a number of new columns to the grid
	 * @param numTimes Number of columns to add 
	 */
	public void addCol(int numTimes) {
		for(int j=0;j<numTimes;j++) {
			int index = this.numCols;
			for(int i=0;i<this.numRows;i++) {
				Cell c = new Cell();
				this.getChildren().add(index,c);
				index+=this.numCols+1; //add one because width of grid increases by one ENTITY_WIDTH
			}
			this.setPrefWidth(this.getPrefWidth()+Entity.ENTITY_WIDTH);
			this.numCols++;
		}
	}

}
