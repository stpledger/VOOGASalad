package frontend.components;

import frontend.IDEView;
import javafx.scene.Node;

public abstract class PropertiesView extends ViewComponent {
	
	public PropertiesView(IDEView v) {
		super(v);
	}

	public Node getNode() {
		return null;	
	}
}
