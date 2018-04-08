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
	 * Creates and sends a Pair with the method name and the arguments to all of the Observers
	 * @param m a string representing the method name to be called in other views
	 * @param args an Object array representing the arguments of a given method
	 */
	public void setMessage(String m, Object[] args) {
		this.methodName = m;
		this.arguments = args;
		message = new Pair<String, Object[]>(methodName, arguments); //Method to be invoked and necessary arguments
		this.setChanged();
		this.notifyObservers(message);
		this.clearChanged();
	}
	
	
}
