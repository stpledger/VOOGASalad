package engine.components;

/**
 * Data component representing y position of entity
 * @author fitzj
 * @author Yameng Liu
 */
public class YPosition extends SingleDataComponent implements Component, DataComponent, ReadDataComponent {
	
	public YPosition(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "YPosition";

	public String getKey() {
		return KEY;
	}
	
}
