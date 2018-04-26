package engine.components;

public class XPosition extends SingleDataComponent {
	
	public XPosition(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "XPosition";

	public String getKey() {
		return KEY;
	}
	
}
