package frontend.components;

import com.sun.org.apache.xerces.internal.dom.ChildNode;

import frontend.IDEView;
import javafx.scene.Node;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
abstract public class ViewComponent extends ChildNode{
	protected static IDEView ideView; 
	public ViewComponent(IDEView v) {
		ideView = v;
	};
	
	public abstract Node getNode();
}
