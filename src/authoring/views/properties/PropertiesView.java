package authoring.views.properties;

import authoring.MainApplication;
import authoring.factories.ElementFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * 
 * @author Dylan Powers
 *
 */
public abstract class PropertiesView {
	
	private final int GRID_SEPARATION = 10;
	private final int HEIGHT = 450;
	private final int WIDTH = 450;
	private GridPane root;
	private Stage stage;
	private final String RESOURCES = "resources.views.Properties/";
	private ElementFactory eFactory = new ElementFactory();

	/**
	 * Initialize the root of this window as a {@code GridPane}.
	 */
	public PropertiesView() {
		root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(GRID_SEPARATION);
		root.setVgap(GRID_SEPARATION);
	}

	/**
	 * Opens the Property Editor window.
	 */
	public void open() {
		stage = new Stage();
		stage.setTitle(this.title());
		root.getStyleClass().add("properties-view");
		stage.setScene(new Scene(root, WIDTH, HEIGHT));
		stage.getScene().getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
		stage.show();
	}
	
	public void close() {
		stage.close();
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
    
    /**
     * Gets and returns the JavaFX-related root of the {@code PropertiesView}.
     * @return the {@code GridPane} root.
     */
	protected GridPane getRoot() {
		return this.root;
	}
	
	protected String getResourcesFilePath() {
		return this.RESOURCES;
	}
	
	protected ElementFactory getElementFactory() {
		return this.eFactory;
	}
}
