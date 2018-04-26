package engine.components;

import java.util.Map;

/**
 * Class to represent the unique name of an entity.
 * @author Dylan Powers
 *
 */
public class Name extends SingleStringComponent {

	public static String KEY = "Name";
	private String name;
	public Name(int pid, String name) {
		super(pid, KEY);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return KEY;
	}
}
