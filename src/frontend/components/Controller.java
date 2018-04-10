package frontend.components;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.util.Pair;
/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class Controller {
	private ArrayList<ViewComponent> myComponents = new ArrayList<ViewComponent>();
	
	/**
	 * Handles all of the calls from one ViewComponent that must be invoked in another ViewComponent.
	 * @param components
	 */
	public Controller(List<ViewComponent> components) {
		myComponents.addAll(components);
		setUpListeners();
	}

	/**
	 * Bind a BroadcastListener to each viewComponent's Broadcast
	 */
	private void setUpListeners() {
		for(ViewComponent tempComponent: myComponents) {
			BroadcastListener tempListener = new BroadcastListener();
			try {
			tempComponent.addObserver(tempListener);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * BroadcastListener is bound to different Broadcast objects and invokes the proper methods
	 * @author Collin Brown(cdb55)
	 *
	 */
	private class BroadcastListener implements Observer {
		/**
		 * Whenever an observable object is changed, this method looks of the corresponding message to be used
		 */
		@Override
		public void update(Observable origin, Object message) {
			Pair<String, Object[]> tempPair = (Pair<String, Object[]>)message;
			String tempMethodName = tempPair.getKey().toString();
			Object[] args = tempPair.getValue();
			for(ViewComponent tempComponent : myComponents) { //Iterate through all the viewComponents
				List<Method> tempMethodList = Arrays.asList(tempComponent.getClass().getDeclaredMethods());
				for(Method m : tempMethodList) { //Iterate through all the methods in a given viewComponent
					if(m.getName().equals(tempMethodName)) { //check to see if the method being checked and the one you're looking for are the same
						try {
							if(m.getParameterCount() == 0) { //If the method requires no parameters
								m.invoke(tempComponent);
							} else {						//If the method requires one or more parameters
								m.invoke(tempComponent, args); 
							}
						} catch (IllegalAccessException e) {
							System.out.println("IllegalAccessException");
						} catch (IllegalArgumentException e) {
							System.out.println("IllegalArgumentException");
						} catch (InvocationTargetException e) {
							System.out.println("InvocationTargetException");
							e.printStackTrace();
						} catch (NullPointerException e) {
							//Blank Catch
							System.out.println("No such method");
						} catch (Exception e) {
							System.out.println("critical failure");
						}
					}
				}
			}
		}
	}
	
	
}
