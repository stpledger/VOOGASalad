package frontend.components;

import java.util.ArrayList;

import frontend.MainApplication;
import frontend.components.PropertiesView.NumberField;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class EntityBuilderView{
	private final int HEIGHT = 450;
	private final int WIDTH = 450;
	private BorderPane root;
	private String entityName;
	private String spriteFilePath;
	private ArrayList<String> entityTypes;
	
	
	public EntityBuilderView (ArrayList<String> eTypes) {
		entityTypes = eTypes;
		this.build();
		this.open();
	}
	/**
	 * Builds the view to be displayed
	 */
	private void build() {
		root = new BorderPane();
		root.setTop(new topMenu());
		root.setBottom(new bottomMenu());
		
	}

	/**
	 * Opens the Property Editor window.
	 */
	private void open() {
		Stage stage = new Stage();
		Scene s = new Scene(root, WIDTH, HEIGHT);
		stage.setTitle("Entity Builder");
		s.getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
		stage.setScene(s);
		stage.show();
	}
	
	private class topMenu extends HBox {
		public topMenu() {
			this.setAlignment(Pos.CENTER_LEFT);
			this.getStyleClass().add("toolbar");
			this.setWidth(WIDTH);
			buildMenu();
		}

		private void buildMenu() {
			//Create prompt text
			Text entityTypePrompt = new Text("Entity Prompt: ");
			entityTypePrompt.getStyleClass().add("toolbar-prompt");
			this.getChildren().add(entityTypePrompt);
			//Create Entity Type selector
			ComboBox entityType = new ComboBox();
			entityType.getStyleClass().add(".entity-builder-combo-box"); //TODO: Make the arrow reappear so people know we mean business
			entityType.getItems().addAll(entityTypes);
			entityType.getSelectionModel().selectFirst();
			this.getChildren().add(entityType);
		}
	}
	
	/**
	 * Bottom Menu of the EntityBuilderView
	 */
	private class bottomMenu extends HBox {
		public bottomMenu() {
			this.setAlignment(Pos.CENTER_RIGHT);
			this.getStyleClass().add("toolbar");
			this.setWidth(WIDTH);
			buildMenu();
			
		}
		
		private void buildMenu() {
			//Create Save Button
			Button b = new Button("Save");
			b.getStyleClass().add("entity-builder-view-button");
			this.getChildren().add(b);
		}
	}


}
