package engine.components;

/**
 * Data component representing y acceleration of entity
 * @author fitzj
 */
public class YAcceleration extends SingleDataComponent implements Component, DataComponent, ReadDataComponent {
	
	public YAcceleration(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "YAcceleration";

	public String getKey() {
		return KEY;
	}
	
}
