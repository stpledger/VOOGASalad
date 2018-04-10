package engine.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.components.Component;
import engine.components.IKeyInput;
import engine.components.KeyInput;
import javafx.scene.input.KeyCode;

public class InputHandler implements ISystem {
	private Map<Integer, List<KeyInput>> handledComponents = new HashMap<>();
	private Map<Integer, Map<String, Component>> handledEntities = new HashMap<>();

	private Set<KeyCode> active;
	
	
	//front end sends here the keycode and than I run components on it
	@Override
	public void addComponent(int pid, Map<String, Component> components) {
		List<KeyInput> newComponents = new ArrayList<>();
		int counter =0;
		for (Component c: components.values()) {
			if( c instanceof KeyInput) {
				newComponents.add((KeyInput) c);
				counter++;
			}
			if(counter>=1) {
			handledEntities.put(pid, components);
		    }
			handledComponents.put(pid, newComponents);	    
	   }
	}
	@Override
	public void removeComponent(int pid) {
		if(handledComponents.containsKey(pid)) {
    		handledComponents.remove(pid);
    		handledEntities.remove(pid);
    	}
		
	}

	@Override
	public void execute(double time) {
		handledComponents.forEach((key, list) -> {
			list.forEach(keyIn -> {
				if(active.contains(keyIn.getCode())) {
					keyIn.getConsumer().accept(handledEntities.get(key));
				}
			});
		});
	}
	@Override
	public void setActives(Set<Integer> actives) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
