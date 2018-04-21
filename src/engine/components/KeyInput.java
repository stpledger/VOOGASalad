package engine.components;

import javafx.scene.input.KeyCode;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * This is the component class that allows the designer to assign an action to an entity based on a key code/key input,
 * such that the user can decide what should be done by the entity either by a preset selection or writing in his/her
 * own code
 *
 * @author cndracos
 */
public class KeyInput extends Component {

	private Map<KeyCode, Consumer> codes = new HashMap<>();

	public static String KEY = "KeyInput";

	public KeyInput(int pid) {
		super(pid, KEY);
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
	public void addCode (KeyCode code, Consumer con) {
		codes.put(code, con);
	}

	public void action(KeyCode key) {
		codes.get(key).accept(null);
	}

	@Override
	public Map<String, String> getParameters(){
		Map<String,String> res = new HashMap<>();
		for(Map.Entry<KeyCode,Consumer> entry : codes.entrySet()) {
			res.put("Key Code", entry.getKey().getName());
		}
		
		return res;
	}

}
