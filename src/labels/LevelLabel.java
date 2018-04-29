package labels;
import java.text.MessageFormat;
import java.util.Map;

import HUD.IHUD;
import authoring.gamestate.Level;
import engine.components.Component;
import engine.components.Health;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
		// this is here to suppress errors
		return 0;
	}

	@Override
	public void update(double newValue) {
		// TODO Auto-generated method stub
		levelProperty.setValue(newValue);
	}

	
}
