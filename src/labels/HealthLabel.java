package labels;
import java.text.MessageFormat;
import java.util.Map;

import HUD.IHUD;
import engine.components.Component;
import engine.components.Health;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

public class HealthLabel extends Label implements IGameStatusLabel{

	private double currentHealth;
	private final String HEALTH_LABEL_NAME = "Health: ";
	private SimpleDoubleProperty healthProperty;
	
	
	public HealthLabel() {
		healthProperty = new SimpleDoubleProperty();
		this.textProperty().bind(healthProperty.asString());
	}

	@Override
	public double extractGameStateValue(Map<String, Component> playerStatusMap) {
		Health health = (Health) playerStatusMap.get(Health.KEY);
		return health.getHealth();
	}

	@Override
	public void update(double newValue) {
		// TODO Auto-generated method stub
		healthProperty.setValue(newValue);
	}

	
}
