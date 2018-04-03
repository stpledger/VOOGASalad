package frontend.components;

import frontend.IDEView;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

public class ComponentTab extends Tab{
	IDEView ideView;
	ScrollPane scrollPane;
	public ComponentTab(String name, IDEView v) {
		super(name);
		ideView = v;
		this.setClosable(false);
		this.getStyleClass().add("component-tab");
		assemble();
	}
	
	private void assemble() {
		scrollPane = new ScrollPane();
		scrollPane.setPrefWidth(ideView.getComponentViewWidth());
		this.setContent(scrollPane);
	}
	
	public Node getNode() {
		return scrollPane;
	}
	
}
