package authoring.views;

import java.io.File;
import java.util.logging.Logger;

import authoring.factories.Toolbar;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class MainView {
	private BorderPane border;
	private Toolbar toolbar;
	private GameEditorView gameEditorView;
	private EntityView componentView;
	
	//GUI Constants
	private static double ideHeight = 600;
	private static double ideWidth = 1200;

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
	public static double getIDEHeight() {
		return ideHeight;
	}

	/**
	 * Sets the height of the gameAuthoringEnvironment
	 * @param ideHeight
	 */
	public static void setIDEHeight(double ideHeight) {
		MainView.ideHeight = ideHeight;
	}

	/**
	 * Gets the width of the gameAuthroingEnvironment
	 * @return
	 */
	public static double getIDEWidth() {
		return ideWidth;
	}

	/**
	 * Sets the width of the GameAuthoringEnvironment
	 * @param ideWidth
	 */
	public static void setIDEWidth(double ideWidth) {
		MainView.ideWidth = ideWidth;
	}
}
