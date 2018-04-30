package engine.components;

/**
 * Data component representing an entity's health.
 * @author fitzj
 *
 */

public class Health extends SingleDataComponent implements Component, DataComponent, ReadDataComponent {
	private double initialHealth;
	public static final String KEY = "Health";
	
	public Health(int pid, double data) {
		super(pid, data);
		initialHealth = data;
	}
	
	public void resetHealth() {
		this.setData(initialHealth);
	}

	public String getKey() {
		return KEY;
	}
	
}
