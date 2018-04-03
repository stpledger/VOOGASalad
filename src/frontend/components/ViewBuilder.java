package frontend.components;

import frontend.IDEView;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class ViewBuilder{
	private Toolbar toolbar;
	private GameEnvironmentView gameEnvironmentView;
	private ComponentView componentView;
	private IDEView ideView;
	
	public ViewBuilder(IDEView v) {
		ideView = v;
		toolbar = new Toolbar(ideView);
		gameEnvironmentView = new GameEnvironmentView(ideView);
		componentView = new ComponentView(ideView);
	}
	
	public Parent build() {
		BorderPane border = new BorderPane();
		border.setTop(toolbar.getNode());
		border.setLeft(componentView.getNode());
		border.setCenter(gameEnvironmentView.getNode());
		return border;
		
	}
}
