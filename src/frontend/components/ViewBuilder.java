package frontend.components;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class ViewBuilder{
	private Toolbar toolbar;
	private GameEnvironmentView gameEnvironmentView;
	private ComponentView componentView;
	
	public ViewBuilder() {
		toolbar = new Toolbar();
		gameEnvironmentView = new GameEnvironmentView();
		componentView = new ComponentView();
	}
	
	public Parent build() {
		BorderPane border = new BorderPane();
		border.setTop(toolbar.getNode());
		border.setLeft(componentView.getNode());
		border.setCenter(gameEnvironmentView.getNode());
		return border;
		
	}
}
