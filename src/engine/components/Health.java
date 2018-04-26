package engine.components;

public class Health extends SingleDataComponent {
	
	public Health(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "Health";

	public String getKey() {
		return KEY;
	}
	
}
