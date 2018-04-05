package frontend.components;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class Controller {
	private ArrayList<ViewComponent> myComponents = new ArrayList<ViewComponent>();
	
	
	public Controller(List<ViewComponent> components) {
		myComponents.addAll(components);
		setUpListeners();
	}

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
	
	private class BroadcastListener implements Observer{
		/**
		 * Whenever an observable object is changed, this method looks of the corresponding message to be used
		 */
		@Override
		public void update(Observable origin, Object message) {
			for(ViewComponent tempComponent : myComponents) {
				List<Method> tempMethodList = Arrays.asList(tempComponent.getClass().getDeclaredMethods());
				for(Method m : tempMethodList) {
					if(m.getName().equals(message)) {
						try {
							m.invoke(tempComponent);
						} catch (IllegalAccessException e) {
							System.out.println("IllegalAccessException");
						} catch (IllegalArgumentException e) {
							System.out.println("IllegalArgumentException");
						} catch (InvocationTargetException e) {
							System.out.println("InvocationTargetException");
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
