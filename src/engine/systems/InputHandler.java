package engine.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.components.Component;
import engine.components.KeyInput;
import javafx.beans.property.SetProperty;
import javafx.collections.SetChangeListener;
import javafx.scene.input.KeyCode;

public class InputHandler implements ISystem {
	private Map<Integer, List<KeyInput>> handledComponents = new HashMap<>();
	private Map<Integer, Map<String, Component>> handledEntities = new HashMap<>();

	private Set<KeyCode> active = new HashSet<>();
	
	
	//front end sends here the keycode and than I run components on it
	@Override
	public void addComponent(int pid, Map<String, Component> components) {
		List<KeyInput> addedComp = new ArrayList<>();
		components.forEach((key, comp) ->{
			if(comp instanceof KeyInput) {
				 addedComp.add((KeyInput) comp);
			}
		 });
		    if(addedComp.size()!=0) {
		    	handledEntities.put(pid, components);
		    	handledComponents.put(pid, addedComp);
		    }
		}
		
	@Override
	public void removeComponent(int pid) {
		if(handledComponents.containsKey(pid)) {
    		handledComponents.remove(pid);
    		handledEntities.remove(pid);
    	}
		
	}
	public void removeSpecificComponent(int pid, KeyCode code) {
		if (handledComponents.containsKey(pid)) {
			handledComponents.get(pid).forEach(comp-> {
				if(comp.getCode()==code) {
					handledComponents.remove(pid, comp);
				}
			});	
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
	
	
	public void addKeySet(SetProperty<KeyCode> actives) {
		actives.addListener((SetChangeListener<? super KeyCode>) set -> {
			KeyCode added = set.getElementAdded();
			KeyCode removed = set.getElementRemoved();
			
			if(added != null) {
				active.add(added);
			}
			if(removed != null) {
				active.remove(removed);
			}
		});
	}
	@Override
	public void setActives(Set<Integer> actives) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComponent(int pid, String componentName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(int pid, String componentName) {
		// TODO Auto-generated method stub
		
	}	
	
	@Override
	public Map<Integer, Map<String, Component>> getHandledComponent() {
		return handledEntities;
	}
}
