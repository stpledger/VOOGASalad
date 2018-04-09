package frontend.components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import frontend.MainApplication;
import frontend.components.PropertiesView.NumberField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class EntityBuilderView{
	private final int HEIGHT = 600;
	private final int WIDTH = 800;
	private final int LEFT_PANEL_WIDTH = 200;
	private TopMenu topMenu;
	private BottomMenu bottomMenu;
	private LeftPanel leftPanel;
	private BorderPane root;
	private String entityName;
	private String spriteFilePath;
	private ArrayList<String> entityTypes;
	private String myEntityType;
	private Stage stage;
	private File imageFile;
	private Image image;
	private List<String> imageExtensions = Arrays.asList(new String[] {".jpg",".png",".jpeg"});
	
	
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
		topMenu = new TopMenu();
		bottomMenu = new BottomMenu();
		leftPanel = new LeftPanel();
		root.setTop(topMenu);
		root.setLeft(leftPanel);
		root.setBottom(bottomMenu);
		
	}

	/**
	 * Opens the Property Editor window.
	 */
	private void open() {
		stage = new Stage();
		Scene s = new Scene(root, WIDTH, HEIGHT);
		stage.setTitle("Entity Builder");
		s.getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
		stage.setScene(s);
		stage.show();
	}
	
	private class TopMenu extends HBox {
		public TopMenu() {
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
			ComboBox<String> entityType = new ComboBox<String>();
			entityType.getStyleClass().add(".entity-builder-combo-box"); //TODO: Make the arrow reappear so people know we mean business
			entityType.getItems().addAll(entityTypes);
			entityType.setMinWidth(150);
			entityType.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					myEntityType = entityType.getSelectionModel().getSelectedItem();
				}
			});
			entityType.setPromptText("Entity Type");
			this.getChildren().add(entityType);
			
			//Create a Load Image button
			Button imageButton = new Button("Load Image");
			imageButton.getStyleClass().add("entity-builder-view-button");
			imageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Open Image File");
					fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Image Filter", imageExtensions ));
					imageFile = fileChooser.showOpenDialog(stage);
					BufferedImage bufferedImage = null;
					try {
						bufferedImage = ImageIO.read(imageFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                image = SwingFXUtils.toFXImage(bufferedImage, null);
					leftPanel.setNewImage(image);
				}		
			});
			this.getChildren().add(imageButton);
			
			
		}
	}
	/**
	 * ScrollPane that holds the current properties of an Entity
	 */
	private class LeftPanel extends ScrollPane {
		private VBox vBox;
		private ImageView imageView;
		public LeftPanel() {
			this.setWidth(LEFT_PANEL_WIDTH);
			vBox = new VBox();
			this.setContent(vBox);
			buildPanel();
		}

		private void buildPanel() {
			//Create an Image View
			imageView = new ImageView();
			imageView.setFitWidth(LEFT_PANEL_WIDTH);
			imageView.setFitHeight(LEFT_PANEL_WIDTH);
			imageView.setImage(new Image("mario.png"));
			vBox.getChildren().add(imageView);
			
		}
		
		/**
		 * Set the current image being displayed in the properties panel
		 */
		public void setNewImage(Image i) {
			imageView.setImage(i);
		}
	}
	
	/**
	 * Bottom Menu of the EntityBuilderView
	 */
	private class BottomMenu extends HBox {
		public BottomMenu() {
			this.setAlignment(Pos.CENTER_RIGHT);
			this.getStyleClass().add("toolbar");
			this.setWidth(WIDTH);
			buildMenu();
			
		}
		
		private void buildMenu() {
			//Create Save Button
			Button saveButton = new Button("Save");
			saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
<<<<<<< HEAD
					//TODO: Make this save the entity
=======
//					broadcast.setMessage("saveEntity", new Object[] {myEntityType, imageFile});
>>>>>>> d5cb50ad7a72d7d22708124d0105fda504e64f91
					stage.close();
					
				}
				
			});
			saveButton.getStyleClass().add("entity-builder-view-button");
			this.getChildren().add(saveButton);
		}
	}


}
