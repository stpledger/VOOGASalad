package engine.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.components.Component;
import engine.components.IKeyInput;

public class InputHandler implements ISystem {
	private Map<Integer, List<IKeyInput>> handledComponents = new HashMap<>();
	private Map<Integer, Map<String, Component>> handledEntities = new HashMap<>();
	private List<String> keyCodes;

	
	InputHandler(List<String> codes){
		keyCodes=codes;
	}
	//front end sends here the keycode and than I run components on it
	@Override
	public void addComponent(int pid, Map<String, Component> components) {
		List<IKeyInput> newComponents = new ArrayList<>();
		int counter =0;
		for (Component c: components.values()) {
			if( c instanceof IKeyInput) {
				newComponents.add((IKeyInput) c);
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
    	}
		
	}

	@Override
	public void execute(double time) {
		for (int pid : handledComponents.keySet()) {  
			for(IKeyInput h : handledComponents.get(pid)) {
				for(String code : keyCodes)
				 h.execute(code, handledEntities.get(pid));
			}
		}
		
	}

	@Override
	public Map<Integer, List<Component>> getAllComponents(){
		return handledComponents;
	}
	
	
	
}
