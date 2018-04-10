package frontend.components;

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
	private GameEnvironmentView gameEnvironmentView;
	private EntityView componentView;
	
	//GUI Constants
	private static double ideHeight = 600;
	private static double ideWidth = 1200;
	private double toolbarHeight = 25;
	
	public MainView() {
		border = new BorderPane();
		gameEnvironmentView = new GameEnvironmentView();
		componentView = new EntityView();
	}
	
	/**
	 * creates the ViewComponents and adds them to the borderPane 
	 */
	public Parent build() {
		border = new BorderPane();
		border.setTop(toolbar);
		border.setLeft(componentView);
		border.setCenter(gameEnvironmentView);
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
	public GameEnvironmentView getGameEnvironmentView() {
		return gameEnvironmentView;
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
