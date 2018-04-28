package labels;
import java.text.MessageFormat;

import HUD.IHUD;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

public class HealthLabel extends Label implements IHUD{

	private double currentHealth;
	private final String HEALTH_LABEL_NAME = "Health: ";
	private SimpleDoubleProperty healthProperty;
	
	
	public HealthLabel(double health) {
		currentHealth = health;
		healthProperty = new SimpleDoubleProperty(currentHealth);
		StringBinding binding = Bindings.createStringBinding(
	            () -> MessageFormat.format(HEALTH_LABEL_NAME+"{0}", healthProperty.getValue()));
		this.textProperty().bind(binding);
	}

	/**
	 * Method for changing between levels
	 */
	@Override
	public void updateHUD() {
		// TODO Auto-generated method stub
		
	}

	
}
