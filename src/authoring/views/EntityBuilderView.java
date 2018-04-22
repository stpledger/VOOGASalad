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
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout.Alignment;

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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class EntityBuilderView extends Stage{
	private final int LEFT_PANEL_WIDTH = 200;
	private final String PROPERTIES_PACKAGE = "resources.menus.Entity/";
	private final String COMPONENT_PREFIX = "engine.components.";
	private Properties tooltipProperties;
	private MenuBar topMenu;
	private HBox bottomMenu;
	private VBox leftPanel;
	private GridPane currentForms;
	private VBox root;
	private ArrayList<String> entityTypes;
	private String myEntityType;
	private File imageFile;
	private Image image;
	private ImageView entityPreview;
	private List<EntityComponentForm> activeForms;
	private List<String> imageExtensions = Arrays.asList(new String[] {".jpg",".png",".jpeg"});
	private BiConsumer<String, Map<Class, Object[]>> onClose;
	private Consumer<MouseEvent> saveOnClick = (e)->{save();};
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
		tooltipProperties = new Properties();
		try {
			tooltipProperties.load(new FileInputStream("src/resources/tooltips/EntityBuilderViewTooltips.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.root = new VBox();
		this.root.setAlignment(Pos.CENTER);
		this.topMenu = buildTypeComboBox();
		this.bottomMenu = buildBottomMenu();
		this.leftPanel = buildLeftPanel();
		this.root.getChildren().add(topMenu);
		this.root.getChildren().add(entityPreview);
		this.root.getChildren().add(leftPanel);
		this.root.getChildren().add(bottomMenu);
		
		this.root.getStyleClass().add("entity-builder-view");
		
	}

	/**
	 * Opens the Property Editor window.
	 */
	private void open() {
		Scene s = new Scene(root);
		this.setTitle("Entity Builder");
		s.getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
		this.setScene(s);
		this.show();
	}
	
	/**
	 * Builds the top menu Bar of the EntityBuilderView
	 * @return MenuBar representing the toolbar
	 */
	private MenuBar buildTypeComboBox() {
			MenuBar menuBar = new MenuBar();
			menuBar.getStyleClass().add("toolbar");
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
			imageFile = fileChooser.showOpenDialog(this);
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
				this.root.getChildren().remove(bottomMenu);
				root.getChildren().add(fillComponentsForms());
				this.root.getChildren().add(bottomMenu);
				this.sizeToScene();
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
			hBox.setAlignment(Pos.CENTER);
			hBox.getStyleClass().add("toolbar");
			
			hBox.getChildren().add(buttonBuilder("save", saveOnClick));
			return hBox;
			
		}
	
	private Button buttonBuilder(String name, Consumer onClick) {
		Button button = new Button(name);
		button.setTooltip(new Tooltip(tooltipProperties.getProperty(name)));
		button.setOnMouseClicked((e)->onClick.accept(e));
		button.getStyleClass().addAll("entity-builder-view-button",name);
		return button;
		
	}
	/**
	 * Saves the current entity
	 */
	private void save(){
		for(EntityComponentForm componentForm : activeForms) {
			Object[] tempArr = componentForm.buildComponent();
			if(tempArr != null) {
			try {
				componentAttributes.put(Class.forName(COMPONENT_PREFIX + componentForm.getName()), tempArr);
			} catch (ClassNotFoundException e1) {
				System.out.println("Error Trying to Save New Entity");
			}}
		}
		onClose.accept(myEntityType, componentAttributes);
		this.close();
			}
	
	/**
	 * Creates the forms and returns them as a GridPane
	 * @return gridPane a gridpane filled with the necessary forms
	 */
	public Node fillComponentsForms() {
			this.root.getChildren().remove(currentForms);
			currentForms = new GridPane();
			currentForms.getStyleClass().add("component-form");
			int currentRow = 0;
			this.activeForms = new ArrayList<>();
			for (String property : ResourceBundle.getBundle(PROPERTIES_PACKAGE + myEntityType).keySet()) {
				if(!property.equals("Sprite") && !property.equals("Position")) {
					EntityComponentForm cf = new EntityComponentForm(property);
					cf.setAlignment(Pos.CENTER);
					this.activeForms.add(cf);
					currentForms.add(cf, 0, currentRow++);
				}
			}
			return currentForms;
	}


}
