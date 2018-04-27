package engine.components;

public class DamageLauncherLifetime extends SingleDataComponent{

	public DamageLauncherLifetime(int pid, double data) {
		super(pid, data);
		
	}
	public static final String KEY = "DamageLauncherLifetime";

	public String getKey() {
		return KEY;
	}
}
