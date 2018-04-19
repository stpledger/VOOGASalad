package authoring.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import javax.imageio.ImageIO;

import authoring.MainApplication;
import authoring.components.EntityComponentForm;
import data.DataRead;
import data.DataWrite;
import engine.components.Component;
import engine.components.Sprite;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
	private final String PROPERTIES_PACKAGE = "resources.menus.Entity/";
	private final String COMPONENT_PREFIX = "engine.components.";
	private MenuBar topMenu;
	private HBox bottomMenu;
	private VBox leftPanel;
	private BorderPane root;
	private ArrayList<String> entityTypes;
	private String myEntityType;
	private Stage stage;
	private File imageFile;
	private Image image;
	private ImageView entityPreview;
	private List<EntityComponentForm> activeForms;
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
		this.root = new BorderPane();
		this.topMenu = buildTopBar();
		this.bottomMenu = buildBottomMenu();
		this.leftPanel = buildLeftPanel();
		this.root.setTop(topMenu);
		this.root.setLeft(leftPanel);
		this.root.setBottom(bottomMenu);
		GridPane tempGridPane = new GridPane();
		tempGridPane.getStyleClass().add("component-form");
		this.root.setCenter(tempGridPane);
		this.root.getStyleClass().add("entity-builder-view");
		
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
	
	/**
	 * Builds the top menu Bar of the EntityBuilderView
	 * @return MenuBar representing the toolbar
	 */
	private MenuBar buildTopBar() {
			MenuBar menuBar = new MenuBar();
			menuBar.getStyleClass().add("toolbar");
			menuBar.setMinWidth(WIDTH);
			menuBar.getMenus().add(buildTypeMenu());
			menuBar.getMenus().add(buildImageMenu());
			return menuBar;
		}

	/**
	 * Builds a menu for managing images of entities
	 * @return a Menu for handling adding and removing images
	 */
	private Menu buildImageMenu() {
		Menu imageMenu = new Menu();
		imageMenu.setText("Image");
		MenuItem addImage = new MenuItem();
		addImage.setText("Add");
		addImage.setOnAction((e)->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Image File");
			fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Image Filter", imageExtensions ));
			imageFile = fileChooser.showOpenDialog(stage);
			try {
				componentAttributes.put(Sprite.class, new Object[] {imageFile.getName()});
				DataRead.importImage( imageFile);
				image = SwingFXUtils.toFXImage(ImageIO.read(imageFile), null);
				updateEntityPreview(image);
			} catch (Exception e1){
				System.out.println("Error loading image");
			}
		});
		imageMenu.getItems().add(addImage);
		return imageMenu;
		}
	
	/**
	 * Updates the image preview for the entity
	 * @param i image to add
	 */
	private void updateEntityPreview(Image i) {
		entityPreview.setImage(i);
		entityPreview.setFitHeight(LEFT_PANEL_WIDTH);
		entityPreview.setFitWidth(LEFT_PANEL_WIDTH);
		//TODO: Make this handle things other than squares
		
	}
	
	/**
	 * Builds the menu to select the Type of Entity
	 * @return Menu typeMenu
	 */
	private Menu buildTypeMenu() {
		Menu typeMenu = new Menu();
		typeMenu.setText("Object Type");
		typeMenu.getStyleClass().add(".entity-builder-combo-box"); 
		for(String et : entityTypes) {
			MenuItem menuItem = new MenuItem();
			menuItem.setText(et);
			menuItem.setOnAction((e)->{
				myEntityType = et;
				typeMenu.setText(et);			
				root.setCenter(fillComponentsForms());
				});
			typeMenu.getItems().add(menuItem);
		}	
		return typeMenu;
	}
	
	/**
	 * Constructs a left panel containing entityPreview
	 * @return VBox leftPanel
	 */
	private VBox buildLeftPanel() {
		VBox vBox = new VBox();
		entityPreview = new ImageView();
		vBox.setMinWidth(LEFT_PANEL_WIDTH);
		vBox.getStyleClass().add("left-panel");
		entityPreview = new ImageView();
		updateEntityPreview(new Image("no_image.jpg"));
		vBox.getChildren().add(entityPreview);
		return vBox;
		}
			
	
	/**
	 * Builds the menu on the buttom of the screen containing the save button
	 * @return HBox bottomMenu
	 */
	private HBox buildBottomMenu() {
			HBox hBox = new HBox();
			hBox.setAlignment(Pos.CENTER_RIGHT);
			hBox.getStyleClass().add("toolbar");
			hBox.setPrefWidth(WIDTH);
			hBox.getChildren().add(buildSaveButton());
			return hBox;
			
		}
		
	/**
	 * Builds the save button
	 * @return Button save button
	 */
	private Button buildSaveButton() {
		Button saveButton = new Button("Save");
		saveButton.setOnMouseClicked((e)->{
				for(EntityComponentForm componentForm : activeForms) {
					Object[] tempArr = componentForm.buildComponent();
					if(tempArr != null) {
					try {
						componentAttributes.put(Class.forName(COMPONENT_PREFIX + componentForm.getName()), tempArr);
					} catch (ClassNotFoundException e1) {
						System.out.println("Error Trying to Save New Entity");
					}
					}
				}
				onClose.accept(myEntityType, componentAttributes);
				stage.close();
			});
		saveButton.getStyleClass().add("entity-builder-view-button");
		return saveButton;
	}
	
	/**
	 * Creates the forms and returns them as a GridPane
	 * @return gridPane a gridpane filled with the necessary forms
	 */
	public Node fillComponentsForms() {
			GridPane gridPane = new GridPane();
			gridPane.getStyleClass().add("component-form");
			int currentRow = 0;
			this.activeForms = new ArrayList<>();
			for (String property : ResourceBundle.getBundle(PROPERTIES_PACKAGE + myEntityType).keySet()) {
				if(!property.equals("Sprite") && !property.equals("Position")) {
					EntityComponentForm cf = new EntityComponentForm(property);
					this.activeForms.add(cf);
					gridPane.add(cf, 0, currentRow++);
				}
			}
			return gridPane;
	}


}
