package frontend.components;

import com.sun.org.apache.xerces.internal.dom.ChildNode;
import javafx.scene.Node;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
abstract public class ViewComponent extends ChildNode{
	

	public abstract Node getNode();

	@Override
	public String getNodeName() {
		return this.getNode().getId();
	}

	@Override
	public short getNodeType() {
		return Short.parseShort(this.getNode().getClass().getName());
	}
}
