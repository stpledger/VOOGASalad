package engine.systems;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import engine.components.Component;
import engine.components.KeyInput;
import javafx.scene.input.KeyCode;

/**
 * System which delegates key codes pressed by the user to the components which contain that key code such that
 * they can then perform their actions
 *
 * @author cndracos, sv116, fitzj
 */
public class InputHandler implements ISystem {
	private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();

	private Set<Integer> activeComponents;
	private Set<KeyCode> activeCodes;

	public InputHandler() {
		activeComponents = new HashSet<>();
		activeCodes = new HashSet<>();
	}

	@Override
	public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(KeyInput.KEY)) {
			
			handledComponents.put(pid, components);
		}
	}

	@Override
	public void removeComponent(int pid) {
		if (handledComponents.containsKey(pid)) {
			handledComponents.remove(pid);
		}
	}

	@Override
	public void setActives(Set<Integer> actives) {
		Set<Integer> myActives = new HashSet<>(actives);
		myActives.retainAll(handledComponents.keySet());
		activeComponents = myActives;
	}

	@Override
	public void execute(double time) {
		for (int id : activeComponents) {
			Map<String, Component> components = handledComponents.get(id);
			KeyInput k = (KeyInput) components.get(KeyInput.KEY);
			k.action(activeCodes, components);
		}
	}


	public void removeCode(KeyCode code) {
		if(activeCodes.contains(code)) {
			activeCodes.remove(code);
		}
	}

	/**
	 * Adds a code to the activeCode list as long as the user is pressing that code
	 * @param code the key the user is currently pressing
	 */
	public void addCode(KeyCode code) {
		activeCodes.add(code);
	}

}
