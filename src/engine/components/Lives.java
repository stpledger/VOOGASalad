package engine.components;

public class Lives extends SingleDataComponent {

	public static final String KEY = "Lives";
	
	public Lives(int pid, double data) {
		super(pid, data);
	}

	public String getKey() {
		return KEY;
	}

}
