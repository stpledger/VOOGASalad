package engine.components;

/**
 * Data component representing acceleration in x direction of an entity
 * @author fitzj
 */
public class XAcceleration extends SingleDataComponent implements Component, DataComponent, ReadDataComponent {
	
	public XAcceleration(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "XAcceleration";

	public String getKey() {
		return KEY;
	}
	
}
