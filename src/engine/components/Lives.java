package engine.components;

public class Lives extends SingleDataComponent {
	
	public static final String KEY = "Lives";
	
	public Lives(int pid, double lives) {
		super(pid, lives);
	}

	public String getKey() {
		return KEY;
	}
}
