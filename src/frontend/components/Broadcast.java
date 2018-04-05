package frontend.components;

import java.lang.reflect.Method;
import java.util.Observable;

import javafx.util.Pair;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
class Broadcast extends Observable {
	private String methodName;
	private Object[] arguments;
	private Pair<String, Object[]> message;
	public Broadcast() {
		super();
	}
	/**
	 * Get the method associated with this object
	 * @return
	 */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * Set the method that will be invoked by the Controller
	 * @param m
	 */
	public void setMessage(String m, Object[] args) {
		this.methodName = m;
		this.arguments = args;
		message = new Pair<String, Object[]>(methodName, arguments);
		this.setChanged();
		this.notifyObservers(message);
		this.clearChanged();
	}
	
	
}
