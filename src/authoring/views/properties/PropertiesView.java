package authoring.views.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import authoring.MainApplication;
import authoring.factories.Element;
import authoring.factories.ElementFactory;
import authoring.factories.ElementType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Abstract properties view class which contains methods to creating and opening a 
 * properties view window, in which the user can edit the properties of a certain aspect
 * of the game.
 * @author Dylan Powers
 * @author Hemanth Yakkali
 */
public abstract class PropertiesView {
	private static final String RESOURCES = "resources.views.Properties/";
	private static final String BUTTON_RESOURCES = "resources.views.Properties/ButtonProperties";
	private static final int GRID_SEPARATION = 10;
	private static final int SIZE = 450;
	private GridPane root;
	private Stage stage;
	private ElementFactory eFactory = new ElementFactory();
	private ArrayList<Element> elements = new ArrayList<>();
	private Properties language = new Properties();

	/**
	 * Initialize the root of this window as a {@code GridPane}.
	 */
	public PropertiesView() {
		this.root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(GRID_SEPARATION);
		root.setVgap(GRID_SEPARATION);
	}

	/**
	 * Opens the Property Editor window.
	 */
	public void open() {
		root.getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
		stage = new Stage();
		stage.setTitle(this.title());
		root.getStyleClass().add("properties-view");
		stage.setScene(new Scene(root));
		stage.getScene().getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
		stage.show();
		this.fill();
		stage.sizeToScene();
		stage.getScene().getWindow().sizeToScene();
	}
	
	/**
	 * Fills the window with the appropriate names and fields.
	 * @param fields a map with component names that map {@code true} if the box should be strictly numeric, and {@code false} if not.
	 */
	protected abstract void fill();

	/**
	 * Get the title that this window should display.
	 * @return the title of the window.
	 */
	protected abstract String title();
	
	protected void createLabels(String propsName) {
		int currentRow = 0;
		ResourceBundle props = this.getResourcesBundle(propsName);
		for (String property : props.keySet()) {
			try {
				Label label = (Label) this.getElementFactory().buildElement(ElementType.Label,props.getString(property));
				this.getRoot().add(label, 0, currentRow);
				this.getElementList().add((Element) label);
				currentRow++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Closes the stage
	 */
	public void close() {
		stage.close();
	}

	/**
	 * Creates a confirmation alert
	 * @param content Content to be shown in the confirmation alert window.
	 * @return Confirmation Alert that contains the specified content.
	 */
	protected Alert makeAlert(String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(content);
		alert.show();
		return alert;
	}

	/**
	 * Gets and returns the JavaFX-related root of the {@code PropertiesView}.
	 * @return the {@code GridPane} root.
	 */
	protected GridPane getRoot() {
		return this.root;
	}

	/**
	 * 
	 * @param propsName Name of the properties view
	 * @return Resourcebundle for a specified filepath
	 */
	protected ResourceBundle getResourcesBundle(String propsName) {
		return ResourceBundle.getBundle(PropertiesView.RESOURCES+propsName);
	}

	/**
	 * 
	 * @return ResourceBundle with names for buttons
	 */
	protected ResourceBundle getButtonBundle() {
		return ResourceBundle.getBundle(PropertiesView.BUTTON_RESOURCES);
	}

	/**
	 * 
	 * @return ElementFactory
	 */
	protected ElementFactory getElementFactory() {
		return this.eFactory;
	}
	
	/**
	 * 
	 * @return List of form elements such as textfields, numberfields, etc. 
	 */
	protected List<Element> getElementList(){
		return this.elements;
	}

	public void setLanguage(Properties lang) {
		language = lang;
		System.out.println(elements.size());
		for(Element e : elements) {
			e.setLanguage(language);
		}
	}

}
