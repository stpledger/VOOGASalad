package engine.components;

import javafx.scene.input.KeyCode;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * This is the component class that allows the designer to assign an action to an entity based on a key code/key input,
 * such that the user can decide what should be done by the entity either by a preset selection or writing in his/her
 * own code
 *
 * @author cndracos
 */
public class KeyInput extends Conditional {

	private Map<KeyCode, Consumer<Object>> codes = new HashMap<>();

	public static String KEY = "KeyInput";
	
	public KeyInput(int pid) {
		super(pid);
	}

	public boolean containsCode (KeyCode key) {
		return codes.containsKey(key);
	}

	/**
	 * This method allows the user to allow a code-action pairing to a specific
	 * entity's keyInput component, allowing multiple actions to one entity
	 *
	 * @param code the keycode that will trigger the action
	 * @param con the action that happens when the key is pressed
	 */
	public void addCode (KeyCode code, Consumer<Object> con) {
		codes.put(code, con);
		this.setCondition(() -> {
			return codes;
		});
		setUpConditional();
	}

	/*public void action(KeyCode key) {
		codes.get(key).accept(null);
	}*/

	@SuppressWarnings("unchecked")
	private void setUpConditional() {
		this.setAction((map, set) -> {
			if(map == null || !(map instanceof Map<?,?>)) return;
			if(set == null || !(set instanceof Set<?>)) return;
			Map<KeyCode,Consumer<Object>> codeMap = (Map<KeyCode, Consumer<Object>>) map;
			Set<KeyCode> actives = (Set<KeyCode>) set;
			codeMap.forEach((key, con) -> {
				if(actives.contains(key)) {
					con.accept(null);
				}
			}); 
		});
	}
	
	public String getKey() { 
		return KEY; 
	}

}


