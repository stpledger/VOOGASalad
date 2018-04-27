package labels;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

public class HealthLabel extends Label{

	private double currentHealth;
	private final String HEALTH_LABEL_NAME = "Health: ";
	
	
	public HealthLabel(double health) {
		this.setText(HEALTH_LABEL_NAME);
		currentHealth = health;
		this.textProperty().bind(new SimpleDoubleProperty(currentHealth).asString());
	}
	
	
}
