package frontend.components;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Observer;

public class Controller {
	private ArrayList<ViewComponent> myComponents = new ArrayList<ViewComponent>();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public Controller(List<ViewComponent> components) {
		myComponents.addAll(components);
		setUpListeners();
	}

	private void setUpListeners() {
		for(ViewComponent tempComponent: myComponents) {
			Observer tempObserver = new BroadcastListener();
			tempComponent.getBroadcast().addObserver(tempObserver);
		}
		
	}
	
	
}
