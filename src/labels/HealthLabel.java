package labels;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

public class HealthLabel extends Label{

	private int currentHealth;
	private final String HEALTH_LABEL_NAME = "Health: ";
	
	
	public HealthLabel(int health) {
		this.setText(HEALTH_LABEL_NAME);
		currentHealth = health;
		this.textProperty().bind(new SimpleIntegerProperty(currentHealth).asString());
	}
	
	
}
