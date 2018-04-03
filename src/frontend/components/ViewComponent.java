package frontend.components;

import frontend.IDEView;
import javafx.scene.Node;

abstract public class ViewComponent {
	protected IDEView ideView; 
	public ViewComponent(IDEView v) {
		ideView = v;
	};
	
	public abstract Node getNode();
}
