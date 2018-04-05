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
abstract public class ViewComponent{
	protected Broadcast broadcast;
	/**
	 * returns the Node representing the Graphic interface of a given ViewComponent
	 * @return
	 */
	public abstract Node getNode();
	
	public ViewComponent() {
		setBroadcast(buildBroadcast());
	}

	protected abstract Broadcast buildBroadcast();
	
	/**
	 * Adds an observer to the broadcast object
	 * @param o
	 */
	public void addObserver(Observer o) {
		broadcast.addObserver(o);
	}
	/**
	 * Gets the broadcast object
	 * @return
	 */
	public Broadcast getBroadcast() {
		return broadcast;
	}
	
	/**
	 * Sets the broadcast object
	 * @param b
	 */
	public void setBroadcast(Broadcast b) {
		broadcast = b;
	}
}

