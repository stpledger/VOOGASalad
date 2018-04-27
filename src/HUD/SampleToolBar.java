package HUD;

import java.util.List;
import java.util.Map;

import GamePlayer.GamePlayerController;
import Menu.LevelSelector;
import engine.components.Component;
import engine.components.Health;
import engine.components.Player;
import engine.components.Score;
import engine.components.Velocity;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import labels.HealthLabel;
import labels.LivesLabel;
import labels.ScoreLabel;
import labels.TimeLabel;
import labels.VelocityLabel;

public class SampleToolBar extends ToolBar{

	private HBox toolbarLayout;
	private HUDFactory gameStatusFactory;
	private int ActiveLevel;
	private Map<String, Component> playerComponentsforLevel;
	private VelocityLabel label4;
	private HealthLabel label2;
	private int count = 0;

	/**
	 * Builds a Sample Tool Bar that acts as the HUD for the game
	 */
	public SampleToolBar(int activeLevel, Map<Integer, Map<String, Component>> PlayerKeys) {
		ActiveLevel = activeLevel;
		playerComponentsforLevel = PlayerKeys.get(ActiveLevel);
//		gameStatusFactory = new HUDFactory(playerComponentsforLevel, listOfStates); //factory for all the labels
//		toolbarLayout = gameStatusFactory.create(listOfStates);
		//constructor to create a Sample Tool Bar
		toolbarLayout = new HBox(250); //adding spacing by 40 units
		Health health = (Health) PlayerKeys.get(activeLevel).get(Health.KEY);
		label2 = new HealthLabel(health.getHealth());
		Player player = (Player) PlayerKeys.get(activeLevel).get(Player.KEY);
		LivesLabel label3 = new LivesLabel(player.getLives());
		Velocity velocity = (Velocity) PlayerKeys.get(activeLevel).get(Velocity.KEY);
		label4 = new VelocityLabel(velocity.getXVel());
//		Score score = (Score) PlayerKeys.get(activeLevel).get(Score.KEY);
//		ScoreLabel label4 = new ScoreLabel(score.getScore());
		toolbarLayout.getChildren().addAll(label2, label3, label4);
		this.getItems().add(toolbarLayout);
	}	
	
	/**
	 * Update the gameState Values
	 */
	public void updateGameStatusLabels() {
		//System.out.println(label4.extractGameStateValue(playerComponentsforLevel));
		label4.update(label4.extractGameStateValue(playerComponentsforLevel));
		label2.update(label2.extractGameStateValue(playerComponentsforLevel));
	}
	
	
	public void updateGameStatusValues(Map<Integer, Map<String, Component>> playerKeys) {
		playerComponentsforLevel = playerKeys.get(ActiveLevel);
	}
	

}
