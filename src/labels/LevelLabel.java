package labels;
import java.util.Map;

import engine.components.Component;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;

public class LevelLabel extends Label implements IGameStatusLabel{

	private final String LEVEL_LABEL_NAME = "Level: ";
	private SimpleDoubleProperty levelProperty;
	
	
	public LevelLabel() {
		levelProperty = new SimpleDoubleProperty();
		this.textProperty().bind(levelProperty.asString());
	}

	@Override
	public double extractGameStateValue(Map<String, Component> playerStatusMap) {
		return 0;
	}

	@Override
	public void update(double newValue) {
		// TODO Auto-generated method stub
		levelProperty.setValue(newValue);
	}

	
}
