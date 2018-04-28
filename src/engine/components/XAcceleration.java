package engine.components;

public class XAcceleration extends SingleDataComponent {
	
	public XAcceleration(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "XAcceleration";

	public String getKey() {
		return KEY;
	}
	
}
