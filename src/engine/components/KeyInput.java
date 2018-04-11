package engine.components;

import javafx.scene.input.KeyCode;

import java.util.Map;
import java.util.function.Consumer;


public class KeyInput extends Component {
	private KeyCode code;
	private Consumer<Map<String, Component>> con;

	public static String KEY = "KeyInput";
	
	public KeyInput(int pid, KeyCode code, Consumer<Map<String, Component>> con) {
		super(pid);
		this.code = code;
		this.con = con;		
	}

	public KeyCode getCode() {
		return code;
	}

	public void setCode(KeyCode code) {
		this.code = code;
	}

	public Consumer<Map<String, Component>> getConsumer() {
		return con;
	}
	
	public String getKey() {
		return KEY;
	}
	

}
