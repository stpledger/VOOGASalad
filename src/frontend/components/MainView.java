package frontend.components;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class MainView{
	private BorderPane border;
	private Toolbar toolbar;
	private GameEnvironmentView gameEnvironmentView;
	private ComponentView componentView;
	
	//GUI Constants
	private static double ideHeight = 600;
	private static double ideWidth = 1200;
	private double toolbarHeight = 25;
	
	public MainView() {
		border = new BorderPane();
		toolbar = new Toolbar();
		gameEnvironmentView = new GameEnvironmentView();
		componentView = new ComponentView();
	}
	
	
	public Parent build() {
		border = new BorderPane();
		border.setTop(toolbar.getNode());
		border.setLeft(componentView.getNode());
		border.setCenter(gameEnvironmentView.getNode());
		return border;
		
	}
	
	public Toolbar getToolbar() {
		return toolbar;
	}
	
	public GameEnvironmentView getGameEnvironmentView() {
		return gameEnvironmentView;
	}
	
	public ComponentView getComponentView() {
		return componentView;
	}

	public static double getIDEHeight() {
		return ideHeight;
	}

	public static void setIDEHeight(double ideHeight) {
		MainView.ideHeight = ideHeight;
	}

	public static double getIDEWidth() {
		return ideWidth;
	}

	public static void setIDEWidth(double ideWidth) {
		MainView.ideWidth = ideWidth;
	}
}
