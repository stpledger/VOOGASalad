package engine.components;

/**
 * Data class representing the width of an entity
 * @author fitzj
 *
 */
public class Width extends SingleDataComponent {
	
	public Width(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "Width";

	public String getKey() {
		return KEY;
	}
	
}
