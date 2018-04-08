package engine.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.input.KeyCode;
import engine.components.Component;
import engine.components.IKeyInput;

public class InputHandler implements ISystem {
	private Map<Integer, List<IKeyInput>> handledComponents = new HashMap<>();

	
	//front end sends here the keycode and than I run components on it
	@Override
	public void addComponent(int pid, Map<String, Component> components) {
		List<IKeyInput> newComponents = new ArrayList<>();
		for (Component c: components.values()) {
			if( c instanceof IKeyInput) {
				newComponents.add((IKeyInput) c);
			}
		}
			handledComponents.put(pid, newComponents);	    
	}

	@Override
	public void removeComponent(int pid) {
		if(handledComponents.containsKey(pid)) {
    		handledComponents.remove(pid);
    	}
		
	}


	public void execute(double time, KeyCode code) {
		for (int pid : handledComponents.keySet()) {
			for(IKeyInput h : handledComponents.get(pid)) {
				 h.execute(code);
			}
		}
		
	}

	@Override
	public void execute(double time) {
		// TODO Auto-generated method stub
		
	}
	
	
}
