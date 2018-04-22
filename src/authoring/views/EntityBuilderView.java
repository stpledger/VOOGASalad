package authoring.views;

import java.io.File;
import java.io.FileInputStream;
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

import authoring.MainApplication;
import authoring.components.EntityComponentForm;
import data.DataRead;
import engine.components.Sprite;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	private final static int LEFT_PANEL_WIDTH = 200;
	private final static String PROPERTIES_PACKAGE = "resources.menus.Entity/";
	private final static String COMPONENT_PREFIX = "engine.components.";
	private Properties tooltipProperties;
	private HBox saveMenu;
	private GridPane currentForms;
	private VBox root;
	private List<String> entityTypes;
	private String myEntityType;
	private ImageView entityPreview;
	private List<EntityComponentForm> activeForms;
	private List<String> imageExtensions = Arrays.asList(new String[] {".jpg",".png",".jpeg"});
	private BiConsumer<String, Map<Class, Object[]>> onClose;
	private Consumer<MouseEvent> saveOnClick = e -> {save();};
	private Consumer<MouseEvent> addImageOnClick = e -> {addImage();};
	private Map<Class, Object[]> componentAttributes = new HashMap<>();
	
	/**
	 * The constructor of the popup window for creating new entities
	 * @param eTypes All of the possible types of entities
	 * @param oC A BiConsumer that will handle the closing of an EntityBuilderView window that requires a string of the type of entity and a Map of Component Classes and an object[] of their argumetns
	 */
	public EntityBuilderView (List<String> eTypes, BiConsumer<String, Map<Class,Object[]>> oC) {
		this.onClose = oC;
		this.entityTypes = (ArrayList<String>) eTypes;
		this.build();
		this.open();
	}
	/**
	 * Builds the view to be displayed
	 */
	private void build() {
		tooltipProperties = new Properties();
		HBox addImageMenu = new HBox();
		try {
			tooltipProperties.load(new FileInputStream("src/resources/tooltips/EntityBuilderViewTooltips.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.root = new VBox();
		this.root.setAlignment(Pos.CENTER);
		ComboBox<String> typeComboBox = buildTypeComboBox();
		this.saveMenu = buildSingleButtonMenu("save", saveOnClick);
		addImageMenu = buildSingleButtonMenu("addImage", addImageOnClick);
		this.entityPreview = new ImageView();
		updateEntityPreview(new Image("no_image.jpg"));
		this.root.getChildren().addAll(typeComboBox, addImageMenu, entityPreview, saveMenu);
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
	private ComboBox buildTypeComboBox() {
		ComboBox<String> comboBox = new ComboBox();
		comboBox.setPromptText("Object Type");
		comboBox.getStyleClass().add("entity-builder-combo-box"); 
		comboBox.setStyle("-fx-prompt-text-fill: red;");
		for(String et : entityTypes) {
			comboBox.getItems().add(et);
		}
		comboBox.setOnAction(e -> {
				myEntityType = ((String) comboBox.getSelectionModel().getSelectedItem());
				root.getChildren().remove(saveMenu);
				root.getChildren().add(fillComponentsForms());
				root.getChildren().add(saveMenu);
				this.sizeToScene();
		});
		return comboBox;
	}
	
			
	
	/**
	 * Builds the menu on the buttom of the screen containing the save button
	 * @return HBox bottomMenu
	 */
	private HBox buildSingleButtonMenu(String name, Consumer onClick) {
			HBox hBox = new HBox();
			hBox.setAlignment(Pos.CENTER);
			hBox.getStyleClass().add("toolbar");
			hBox.getChildren().add(buttonBuilder(name, onClick));
			return hBox;
			
		}
	/**
	 * Builds a button using a string as a name and a Consumer for the onClick method. CSS is based on the name and Tooltip and Text are based on properties files
	 * @param name the name of the button
	 * @param onClick the consumer to be called on click
	 * @return Button
	 */
	private Button buttonBuilder(String name, Consumer onClick) {
		Button button = new Button(name);
		button.setTooltip(new Tooltip(tooltipProperties.getProperty(name)));
		button.setOnMouseClicked(e ->onClick.accept(e));
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
	 * adds an image to the preview
	 */
	private void addImage() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image File");
		fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Image Filter", imageExtensions ));
		File imageFile = fileChooser.showOpenDialog(this);
		try {
			componentAttributes.put(Sprite.class, new Object[] {imageFile.getName()});
			DataRead.loadImage(imageFile);
			Image image = SwingFXUtils.toFXImage(ImageIO.read(imageFile), null);
			updateEntityPreview(image);
		} catch (Exception e1){
			System.out.println("Error loading image");

		}
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
					currentRow++;
					currentForms.add(cf, 0, currentRow);
				}
			}
			return currentForms;
	}


}
