package authoring.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.sun.prism.paint.Color;

import authoring.entities.Entity;
import authoring.entities.data.EntityLoader;
import authoring.factories.ClickElementType;
import authoring.factories.ElementFactory;
import authoring.views.properties.LocalPropertiesView;
import authoring.views.properties.PropertiesView;
import engine.components.Component;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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
	
	private final String DEFAULT_STYLE = "-fx-background-color: rgba(0, 0, 0, 0); -fx-border-color: black";
	private final String DRAGGED_OVER_STYLE = "-fx-background-color: #1CFEBA";

	private int numRows;
	private int numCols;
	private List<List<Cell>> cells;
	private int numberOfCells = 0;
	private ElementFactory eFactory;
	private Properties language = new Properties();
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Initializes the grid with a given number of rows and columns
	 * @param width the desired width of the grid
	 * @param height the desired height of the grid
	 */
	public Grid(int width, int height) {
		this.numRows = height/Entity.ENTITY_HEIGHT;
		this.numCols = width/Entity.ENTITY_WIDTH;
		this.cells = new ArrayList<>();
		this.eFactory = new ElementFactory();
		this.setBackground(new Background(new BackgroundImage(new Image("File:data/Collin.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
		for (int i = 0; i < this.numRows; i++) {
			cells.add(new ArrayList<>());
        		for (int j = 0; j < this.numCols; j++) {
            		Cell c = new Cell(numberOfCells++);
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
            			createMenu(c,img);
            			c.getChildren().add(img);
            			try {
            				Entity en = el.buildEntity(numberOfCells, db.getString());
            				c.setEntity(en);
            			} catch (Exception e1) {
            				// TODO Auto-generated catch block
            				e1.printStackTrace();
            			}
            			e.setDropCompleted(true);
            			e.consume();
            		});
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
            		cells.get(i).add(c);
            		this.add(c, j, i);
            	}
		}
		this.setPrefSize(width, height);
	}
	
	private void createMenu(Cell cell, ImageView img) {
		img.setOnMouseClicked(e->{
			if(e.getButton().equals(MouseButton.PRIMARY)) {
				System.out.println("cocks!");
				ContextMenu cMenu = new ContextMenu();
				try {
					MenuItem addCol = (MenuItem) eFactory.buildClickElement(ClickElementType.MenuItem, "cocks!", e2->System.out.println("cocks!"));
					MenuItem addRow = (MenuItem) eFactory.buildClickElement(ClickElementType.MenuItem, "cocks!", e2->System.out.println("cocks!"));
					MenuItem addFiveCol = (MenuItem) eFactory.buildClickElement(ClickElementType.MenuItem, "cocks!", e2->System.out.println("cocks!"));
					MenuItem addFiveRow = (MenuItem) eFactory.buildClickElement(ClickElementType.MenuItem,"cocks!", e2->System.out.println("cocks!"));
					MenuItem cancel = (MenuItem) eFactory.buildClickElement(ClickElementType.MenuItem, language.getProperty("cancel"), e2->cMenu.hide());
					cMenu.getItems().addAll(addCol,addRow,addFiveCol,addFiveRow,cancel);
				} catch (Exception e2) {
					LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e2);
				}
				cMenu.show(this, e.getScreenX(), e.getScreenY());
				cMenu.setAutoHide(true);
			}
		});
	}
	
	private void addCol(ImageView img, int numTimes) {
		img.setFitWidth(img.getFitWidth()+numTimes*Entity.ENTITY_WIDTH);
	}
	
	private void addRow(ImageView img, int numTimes) {
		img.setFitHeight(img.getFitHeight()+numTimes*Entity.ENTITY_HEIGHT);
	}
	
	private void setNeighbors() {
		
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
