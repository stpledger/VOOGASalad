package engine.components;

public class YVelocity extends SingleDataComponent {
	
	public YVelocity(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "YVelocity";

	public String getKey() {
		return KEY;
	}
	
}
