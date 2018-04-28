package engine.components;

public class DamageValue extends SingleDataComponent {
	
	public DamageValue(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "DamageValue";

	public String getKey() {
		return KEY;
	}
}
