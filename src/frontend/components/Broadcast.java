package frontend.components;

import java.lang.reflect.Method;
import java.util.Observable;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
class Broadcast extends Observable {
	private String message;
	public Broadcast() {
		super();
	}
	/**
	 * Get the method associated with this object
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * Set the method that will be invoked by the Controller
	 * @param m
	 */
	public void setMessage(String m) {
		this.message = m;
		this.setChanged();
		this.notifyObservers(message);
		this.clearChanged();
	}
	
	
}
