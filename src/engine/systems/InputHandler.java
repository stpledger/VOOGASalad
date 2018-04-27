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
 * @author cndracos
 */
public class InputHandler implements ISystem {
	private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();

	private Set<Integer> activeComponents = new HashSet<>();
	private Set<KeyCode> activeCodes = new HashSet<>();


	@Override
	public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(KeyInput.KEY)) {
			Map<String, Component> newComponents = new HashMap<>();
			newComponents.put(KeyInput.KEY, components.get(KeyInput.KEY));
			handledComponents.put(pid, newComponents);
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

			for (KeyCode key : activeCodes) {
				if (k.containsCode(key)) {
					k.action(key);
				}
			}
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
