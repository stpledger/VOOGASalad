package authoring.views;

import java.io.File;

import authoring.factories.Toolbar;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class MainView {
	private static final double DEFAULT_HEIGHT = 600;
	private static final double DEFAULT_WIDTH = 1200;
	private BorderPane border;
	private Toolbar toolbar;
	private GameEditorView gameEditorView;
	private EntityView componentView;
	
	//GUI Constants
	private double ideHeight = DEFAULT_HEIGHT;
	private double ideWidth = DEFAULT_WIDTH;

	/**
	 * Creates an instance of GameAuthoringEnvironment Based on a file
	 * @param selectedFile the selected file to load a game from
	 */
	public MainView(File selectedFile) {
		border = new BorderPane();
		gameEditorView = new GameEditorView();
		componentView = new EntityView();
	}
	/**
	 * Creates a new instance of GameAuthoringEnvironment
	 * 
	 */
	public MainView() {
		border = new BorderPane();
		gameEditorView = new GameEditorView();
		componentView = new EntityView();
	}

	/**
	 * creates the ViewComponents and adds them to the borderPane 
	 */
	public Parent build() {
		border = new BorderPane();
		border.setTop(toolbar);
		border.setLeft(componentView);
		border.setCenter(gameEditorView);
		return border;

	}

	/**
	 * Returns the Toolbar object
	 * @return
	 */
	public Toolbar getToolbar() {
		return toolbar;
	}

	/**
	 * Returns the gameEnvironmentView object
	 * @return
	 */
	public GameEditorView getGameEnvironmentView() {
		return gameEditorView;
	}

	/**
	 * Returns the ComponentView object
	 * @return
	 */
	public EntityView getComponentView() {
		return componentView;
	}

	/**
	 * Returns the height of the gameAuthoringEnvironment
	 * @return
	 */
	public double getIDEHeight() {
		return ideHeight;
	}

	/**
	 * Sets the height of the gameAuthoringEnvironment
	 * @param ideHeight
	 */
	public  void setIDEHeight(double ideHeight) {
		this.ideHeight = ideHeight;
	}

	/**
	 * Gets the width of the gameAuthroingEnvironment
	 * @return
	 */
	public double getIDEWidth() {
		return ideWidth;
	}

	/**
	 * Sets the width of the GameAuthoringEnvironment
	 * @param ideWidth
	 */
	public void setIDEWidth(double ideWidth) {
		this.ideWidth = ideWidth;
	}
}
