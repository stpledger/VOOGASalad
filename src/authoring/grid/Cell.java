package authoring.grid;

import authoring.entities.Entity;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 * Defines a cell in the grid. The cell will hold nothing when an entity has not been
 * placed inside of it, but will hold an entity when the user has placed an entity inside of it.
 * @author Dylan Powers
 *
 */
public class Cell extends Pane {

	/**
	 * To initialize a blank cell
	 */
	public Cell() {
		this.setPrefWidth(Entity.ENTITY_WIDTH);
		this.setPrefHeight(Entity.ENTITY_HEIGHT);
		this.setStyle("-fx-border-color: black");
		this.setOnDragEntered(e -> this.setStyle("-fx-background-color: #1CFEBA"));
		this.setOnDragExited(e -> this.setStyle("-fx-border-color: black"));
		this.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent e) {
				if (e.getGestureSource() != this && e.getDragboard().hasString()) {
					e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				e.consume();
			}
		});
		this.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent e) {
				Dragboard db = e.getDragboard();
				System.out.printf(db.getString());
				e.setDropCompleted(true);
				e.consume();
			}
		});
	}
	
}
