package frontend.components;

import java.util.Observable;
import java.util.Observer;

class BroadcastListener implements Observer{

	@Override
	public void update(Observable origin, Object message) {
		if(message.getClass().getTypeName().equals("Method")) {
			System.out.println("nut");
		}
	}

}
