package frontend.components;

import java.util.Observable;
import java.util.Observer;

import com.sun.org.apache.xerces.internal.dom.ChildNode;
import javafx.scene.Node;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
abstract public class ViewComponent extends ChildNode{
	protected Broadcast broadcast;
	
	public abstract Node getNode();
	
	public ViewComponent() {
		setBroadcast(buildBroadcast());
	}

	protected abstract Broadcast buildBroadcast();

	@Override
	public String getNodeName() {
		return this.getNode().getId();
	}

	@Override
	public short getNodeType() {
		return Short.parseShort(this.getNode().getClass().getName());
	}
	
	public void addObserver(Observer o) {
		broadcast.addObserver(o);
	}

	public Broadcast getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(Broadcast b) {
		broadcast = b;
	}
}

