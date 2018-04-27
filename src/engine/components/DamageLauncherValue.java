package engine.components;


public class DamageLauncherValue extends SingleDataComponent {

	
	public DamageLauncherValue(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "DamageLauncherValue";

	public String getKey() {
		return KEY;
	}
	
}