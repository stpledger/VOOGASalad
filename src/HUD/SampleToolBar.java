package HUD;

import java.util.Map;

import GamePlayer.GamePlayerController;
import Menu.LevelSelector;
import engine.components.Component;
import engine.components.Health;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import labels.HealthLabel;
import labels.TimeLabel;

public class SampleToolBar extends ToolBar implements IHUD{

	/**
	 * Builds a Sample Tool Bar that acts as the HUD for the game
	 */
	public SampleToolBar(GamePlayerController g, Map<Integer, Map<String, Component>> PlayerKeys) {
		//constructor to create a Sample Tool Bar
		HBox toolbarLayout = new HBox(250); //adding spacing by 40 units
		Health health = (Health) PlayerKeys.get(1).get(Health.KEY);
		HealthLabel label2 = new HealthLabel(health.getData());
		TimeLabel label3 = new TimeLabel(0);
		toolbarLayout.getChildren().addAll(label2);
		this.getItems().add(toolbarLayout);
	}
	
	
	@Override
	public void updateHUD() {
		// TODO Auto-generated method stub
		
	}

}
