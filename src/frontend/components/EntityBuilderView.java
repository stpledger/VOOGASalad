package frontend.components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.imageio.ImageIO;

import data.DataRead;
import data.DataWrite;
import engine.components.Sprite;
import frontend.MainApplication;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class EntityBuilderView{
	private final int HEIGHT = 600;
	private final int WIDTH = 800;
	private final int LEFT_PANEL_WIDTH = 200;
	private TopMenu topMenu;
	private BottomMenu bottomMenu;
	private LeftPanel leftPanel;
	private BorderPane root;
	private ArrayList<String> entityTypes;
	private String myEntityType;
	private Stage stage;
	private File imageFile;
	private Image image;
	private List<String> imageExtensions = Arrays.asList(new String[] {".jpg",".png",".jpeg"});
	private BiConsumer<String, Map<Class, Object[]>> onClose;
	private Map<Class, Object[]> componentAttributes = new HashMap<Class, Object[]>();
	
	/**
	 * The constructor of the popup window for creating new entities
	 * @param eTypes All of the possible types of entities
	 * @param oC A BiConsumer that will handle the closing of an EntityBuilderView window that requires a string of the type of entity and a Map of Component Classes and an object[] of their argumetns
	 */
	public EntityBuilderView (ArrayList<String> eTypes, BiConsumer<String, Map<Class,Object[]>> oC) {
		onClose = oC;
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

	private class TopMenu extends MenuBar {
		public TopMenu() {
			this.getStyleClass().add("toolbar");
			this.setWidth(WIDTH);
			buildMenu();
		}

		private void buildMenu() {
			//Create Entity Type selector
			Menu typeMenu = new Menu();
			typeMenu.setText("Object Type");
			typeMenu.getStyleClass().add(".entity-builder-combo-box"); //TODO: Make the arrow reappear so people know we mean business
			for(String et : entityTypes) {
				MenuItem menuItem = new MenuItem();
				menuItem.setText(et);
				menuItem.setOnAction((e)->{myEntityType = et; typeMenu.setText(et);});
				typeMenu.getItems().add(menuItem);
			}
			this.getMenus().add(typeMenu);
			
			//Create the Image Menu
			Menu imageMenu = new Menu();
			imageMenu.setText("Image");
			//Menu item for loading a new image
			MenuItem addImage = new MenuItem();
			addImage.setText("Add");
			addImage.setOnAction((e)->{
				//Handles choosing a new file
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Image File");
				fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Image Filter", imageExtensions ));
				imageFile = fileChooser.showOpenDialog(stage);
				//Build the spriteComponent for a given entity
				try {
					componentAttributes.put(Sprite.class, new Object[] {imageFile.getName()});
					DataRead.importImage( imageFile);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					image = SwingFXUtils.toFXImage(ImageIO.read(imageFile), null);
				} catch (IOException e1) {
					System.out.println("Error loading image");
				}
				leftPanel.setNewImage(image);
			});
			imageMenu.getItems().add(addImage);	
			
			//Menu item for removing an image
			MenuItem removeImage = new MenuItem();
			removeImage.setText("Remove");
			removeImage.setOnAction((e)->{
				imageFile = null;
				image = null;
				leftPanel.setNewImage(new Image("Mario.png"));
			});
			imageMenu.getItems().add(removeImage);
			this.getMenus().add(imageMenu);
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
		/**
		 * Builds the left-side Panel
		 */
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
		
		/**
		 * Builds the menu
		 */
		private void buildMenu() {
			//Create Save Button
			Button saveButton = new Button("Save");
			saveButton.setOnMouseClicked((e)->{
					onClose.accept(myEntityType, componentAttributes);
					stage.close();
				});
			saveButton.getStyleClass().add("entity-builder-view-button");
			this.getChildren().add(saveButton);
		}
	}


}
