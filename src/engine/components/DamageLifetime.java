package engine.components;

public class DamageLifetime extends SingleDataComponent {
	
	public DamageLifetime(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "DamageLifetime";

	public String getKey() {
		return KEY;
	}
	
}
