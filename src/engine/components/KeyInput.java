package engine.components;

import javafx.scene.input.KeyCode;

import java.util.Map;
import java.util.HashMap;

public class KeyInput extends Component {

	private Map<KeyCode, Runnable> codes = new HashMap<>();

	public static String KEY = "KeyInput";

	public KeyInput(int pid) {
		super(pid);
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

	public String getKey() { return KEY; }

	@Override
	public Map<String, String> getParameters(){
		Map<String,String> res = new HashMap<>();
		for(Map.Entry<KeyCode,Runnable> entry : codes.entrySet()) {
			res.put("Key Code", entry.getKey().getName());
		}
		
		return res;
	}

}
