package engine.components;

public class YPosition extends SingleDataComponent {
	
	public YPosition(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "YPosition";

	public String getKey() {
		return KEY;
	}
	
}
