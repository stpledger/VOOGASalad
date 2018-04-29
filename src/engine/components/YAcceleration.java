package engine.components;

public class YAcceleration extends SingleDataComponent {
	
	public YAcceleration(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "YAcceleration";

	public String getKey() {
		return KEY;
	}
	
}
