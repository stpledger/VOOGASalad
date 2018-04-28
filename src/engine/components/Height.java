package engine.components;

/**
 * Data component representing an entity's height.
 * @author fitzj
 *
 */
public class Height extends SingleDataComponent {
	
	public Height(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "Height";

	public String getKey() {
		return KEY;
	}
	
}

