package authoring.grid;

import java.util.ArrayList;
import java.util.List;

import com.sun.prism.paint.Color;

import authoring.entities.Entity;
import authoring.entities.data.EntityLoader;
import authoring.views.properties.LocalPropertiesView;
import authoring.views.properties.PropertiesView;
import engine.components.Component;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

/**
 * A container class for all of the cells in a grid, which each represent entities if they are filled.
 * @author Dylan Powers
 * @author Hemanth Yakkali(hy115)
 *
 */
public class Grid extends GridPane {

	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 600;

	private int numRows;
	private int numCols;
	private List<List<Cell>> cells;
	private int numberOfCells = 0;
	/**
	 * Initializes the grid with a given number of rows and columns
	 * @param width the desired width of the grid
	 * @param height the desired height of the grid
	 */
	public Grid(int width, int height) {
		this.numRows = height/Entity.ENTITY_HEIGHT;
		this.numCols = width/Entity.ENTITY_WIDTH;
		this.cells = new ArrayList<>();
		this.setBackground(new Background(new BackgroundImage(new Image("File:data/Collin.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
		for (int i = 0; i < this.numRows; i++) {
			cells.add(new ArrayList<>());
        		for (int j = 0; j < this.numCols; j++) {
            		Cell c = new Cell(numberOfCells++);
            		cells.get(i).add(c);
            		this.add(c, j, i);
            	}
		}
		this.setPrefSize(width, height);
	}
	
	private void setNeighbors(double width, double height) {
		
		double actualWidth = width - width % Entity.ENTITY_WIDTH;
		double actualHeight = height - height % Entity.ENTITY_HEIGHT;
		
		for(int i=0;i<this.numRows;i++) {
			for(int j=0;j<this.numCols;j++) {
				Cell cell = cells.get(i).get(j);
				
			}
		}
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
