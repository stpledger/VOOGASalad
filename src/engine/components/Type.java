package engine.components;

import java.util.Map;

/**
 * Class to represent the type of an entity. Refer to the package {@code authoring.entities} for more information.
 * @author Dylan Powers
 *
 */
public class Type implements Component {
	public String KEY = "Type";
	private String type;
	private int pid;

	public Type(int pid, String type) {
		this.pid = pid;
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public int getPID(){
		return pid;
	}

	public String getKey(){
		return KEY;
	}
}
