package authoring.grid;

import java.util.ArrayList;
import java.util.List;

import authoring.entities.Entity;
import authoring.entities.data.EntityLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
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

	/**
	 * Initializes the grid with a given number of rows and columns
	 * @param width the desired width of the grid
	 * @param height the desired height of the grid
	 */
	public Grid(int width, int height) {
		this.numRows = height/Entity.ENTITY_HEIGHT;
		this.numCols = width/Entity.ENTITY_WIDTH;
		this.cells = new ArrayList<>();

		for (int i = 0; i < this.numRows; i++) {
			cells.add(new ArrayList<>());
			for (int j = 0; j < this.numCols; j++) {
				Cell cell = new Cell();
				cell.setOnDragEntered(e-> cell.setStyle("-fx-background-color: #1CFEBA"));
				cell.setOnDragExited(e -> cell.setStyle("-fx-border-color: black"));
				cell.setOnDragOver(e -> {
					if (e.getGestureSource() != cell && e.getDragboard().hasString()) {
						e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
					}
					e.consume();
				});
				cell.setOnDragDropped(e -> {
					Dragboard db = e.getDragboard();
					EntityLoader el = new EntityLoader();
					ImageView img = new ImageView(db.getImage());
					//            			img.fitWidthProperty().bind(cell.widthProperty());
					//            			img.fitHeightProperty().bind(cell.heightProperty());
					img.setOnMouseClicked(e1->{
						if(e1.getButton().equals(MouseButton.SECONDARY)) {
							cell.setPrefWidth(Entity.ENTITY_WIDTH*3);
							System.out.println(GridPane.getColumnIndex(cell));
							System.out.println(GridPane.getRowIndex(cell));
						}
					});
					cell.getChildren().add(img);
					try {
						el.buildEntity(0, db.getString());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.setDropCompleted(true);
					e.consume();
				});
				cells.get(i).add(cell);
				this.add(cell, j, i);
			}
		}
		this.setPrefSize(width, height);
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
			for(int i = 0; i < this.numCols; i++) {
				Cell c = new Cell();
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
				Cell c = new Cell();
				this.cells.get(i).add(c);
				this.add(c, this.numCols, i);
			}
			this.setPrefWidth(this.getPrefWidth() + Entity.ENTITY_WIDTH);
			this.numCols++;
		}
	}

}
