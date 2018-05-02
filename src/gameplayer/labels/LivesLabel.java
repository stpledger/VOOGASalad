package GamePlayer.labels;
import java.util.Map;

import engine.components.Component;
import engine.components.Lives;
import engine.components.Player;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;

public class LivesLabel extends Label implements IGameStatusLabel{

	private final String LIVES_LABEL_NAME = "Lives: ";
	private SimpleDoubleProperty livesProperty;
	
	
	public LivesLabel() {

		livesProperty = new SimpleDoubleProperty();
		this.textProperty().bind(livesProperty.asString());
	}


	@Override
	public double extractGameStateValue(Map<String, Component> playerStatusMap) {
		Player player = (Player) playerStatusMap.get(Player.KEY);

		return 0;
	}


	@Override
	public void update(double newValue) {
		// TODO Auto-generated method stub
		livesProperty.set(newValue);
	}
	
	
}
