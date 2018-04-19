package engine.components;

public class Height extends SingleDataComponent {
	
	public Height(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "Height";

	public String getKey() {
		return KEY;
	}
	
}

