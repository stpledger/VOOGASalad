package engine.components;

/**
 * Data component representing x velocity of entity
 * @author fitzj
 */
public class XVelocity extends SingleDataComponent implements Component, DataComponent, ReadDataComponent {
	
	public XVelocity(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "XVelocity";

	public String getKey() {
		return KEY;
	}
	
}
