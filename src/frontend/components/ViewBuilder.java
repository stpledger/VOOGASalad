package frontend.components;

import frontend.IDEView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class ViewBuilder{
	private BorderPane border;
	private Toolbar toolbar;
	private GameEnvironmentView gameEnvironmentView;
	private ComponentView componentView;
	private IDEView ideView;
	
	public ViewBuilder(IDEView v) {
		border = new BorderPane();
		ideView = v;
		toolbar = new Toolbar(ideView);
		gameEnvironmentView = new GameEnvironmentView(ideView);
		componentView = new ComponentView(ideView);
	}
	
	public void addTempNode(Node n) {
		border.getChildren().add(n);
		ideView.update();
	}
	
	public Parent build() {
		border = new BorderPane();
		border.setTop(toolbar.getNode());
		border.setLeft(componentView.getNode());
		border.setCenter(gameEnvironmentView.getNode());
		return border;
		
	}
}
