package frontend.components;

import java.lang.reflect.Method;
import java.util.Observable;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
class Broadcast extends Observable {
	private Method method;
	public Broadcast() {
	}
	/**
	 * Get the method associated with this object
	 * @return
	 */
	public Method getMethod() {
		return method;
	}
	/**
	 * Set the method that will be invoked by the Controller
	 * @param m
	 */
	public void setMethod(Method m) {
		this.method = m;
		this.notifyObservers(method);
	}
	
	
}
