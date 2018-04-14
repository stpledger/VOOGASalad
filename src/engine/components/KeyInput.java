package engine.components;

import javafx.scene.input.KeyCode;

import java.util.Map;
import java.util.HashMap;

public class KeyInput extends Component {

	private Map<KeyCode, Runnable> codes = new HashMap<>();

	public static String KEY = "KeyInput";

	public KeyInput(int pid, KeyCode code, Runnable con) {
		super(pid, KEY);
		codes.put(code, con);
	}

	public boolean containsCode (KeyCode key) {
		return codes.containsKey(key);
	}

	public void addCode (KeyCode code, Runnable con) {
		codes.put(code, con);
	}

	public void action(KeyCode key) {
		codes.get(key).run();
	}

	public static String getKey() { return KEY; }

}
