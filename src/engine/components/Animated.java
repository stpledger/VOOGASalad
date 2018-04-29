package engine.components;

public class Animated extends FlagComponent {

	public static final String KEY = "Animated";
	
	public Animated(int pid) {
		super(pid);
	}

	@Override
	public String getKey() {
		return KEY;
	}

}
