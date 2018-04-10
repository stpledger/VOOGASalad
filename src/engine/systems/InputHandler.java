package engine.systems;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.components.Component;
import engine.components.KeyInput;
import javafx.scene.input.KeyCode;


public class InputHandler implements ISystem {
	private Map<Integer, KeyInput> handledComponents = new HashMap<>();

	private Set<KeyCode> activeCodes = new HashSet<>();


	@Override
	public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(KeyInput.getKey())) {
			handledComponents.put(pid, (KeyInput) components.get(KeyInput.getKey()));
		}
	}

	@Override
	public void removeComponent(int pid) {
		if (handledComponents.containsKey(pid)) {
			handledComponents.remove(pid);
		}
	}

	public void addCode (KeyCode code) {
		activeCodes.add(code);
	}

	@Override
	public void setActives(Set<Integer> actives) {
		//will get around to this
	}

	@Override
	public void execute(double time) {
		for (int id : handledComponents.keySet()) {
			KeyInput k = handledComponents.get(id);
				for (KeyCode key : activeCodes) {
					if (k.containsCode(key)) {
						k.action(key);
					}
			}
		}
		activeCodes.clear();
	}
}
