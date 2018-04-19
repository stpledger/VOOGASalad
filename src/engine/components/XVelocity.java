package engine.components;

public class XVelocity extends SingleDataComponent {
	
	public XVelocity(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "XVelocity";

	public String getKey() {
		return KEY;
	}
	
}
