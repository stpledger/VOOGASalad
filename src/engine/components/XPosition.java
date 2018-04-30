package engine.components;

/**
 * Data component representing x position of entity
 * @author fitzj
 */
public class XPosition extends SingleDataComponent implements Component, DataComponent, ReadDataComponent {
	
	public XPosition(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "XPosition";

	public String getKey() {
		return KEY;
	}
	
}
