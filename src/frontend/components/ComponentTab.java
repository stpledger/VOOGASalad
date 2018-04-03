package frontend.components;

import frontend.IDEView;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Collin Brown(Cdb55)
 *
 */
public class ComponentTab extends Tab{
	IDEView ideView;
	GridPane pane;
	ScrollPane externalPane;
	public ComponentTab(String name, IDEView v) {
		super(name);
		ideView = v;
		this.setClosable(false);
		this.getStyleClass().add("component-tab");
		assemble();
	}
	
	private void assemble() {
		externalPane = new ScrollPane();
		externalPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		pane = new GridPane();
		pane.setPrefWidth(ideView.getComponentViewWidth());
		//TODO: Make this dynamic to handle each individual component
		for(int i = 0; i < 9; i++) {
		pane.add(new ComponentBox("bleh", "temp"), 0, i);
		pane.add(new ComponentBox("bleh", "temp"), 1, i);
		pane.add(new ComponentBox("bleh", "temp"), 2, i);
		}
		externalPane.setContent(pane);
		this.setContent(externalPane);
	}
	/**
	 * 
	 * @return
	 */
	public Node getNode() {
		return pane;
	}
	
	private class ComponentBox extends Rectangle{
		public ComponentBox(String name, String imagePath) {
			this.setWidth(ideView.getComponentViewWidth()/3);
			this.setHeight(ideView.getComponentViewWidth()/3);
			this.getStyleClass().add("component-box");
		}
	}
	
}
