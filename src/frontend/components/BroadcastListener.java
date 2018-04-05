package frontend.components;

import java.util.Observable;
import java.util.Observer;

class BroadcastListener implements Observer{

	@Override
	public void update(Observable origin, Object message) {
		System.out.println(message.toString());
	}
	

}
