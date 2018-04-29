package HUD;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import GamePlayer.GamePlayerController;
import Menu.LevelSelector;
import engine.components.Component;
import engine.components.Health;
import engine.components.Lives;
import engine.components.Player;
import engine.components.Score;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import labels.HealthLabel;
import labels.IGameStatusLabel;
import labels.LivesLabel;
import labels.ScoreLabel;
import labels.TimeLabel;
import labels.VelocityLabel;

public class SampleToolBar extends ToolBar{

	private HBox toolbarLayout;
	private HUDFactory gameStatusFactory;
	private int ActiveLevel;
	private Map<String, Component> playerComponentsforLevel;
	private Map<String, Boolean> listOfStatusLabels;
	private List<IGameStatusLabel> labelsToDisplay;
	private List<String> approvedLabels;


	/**
	 * Builds a Sample Tool Bar that acts as the HUD for the game
	 */
	public SampleToolBar(int activeLevel, Map<Integer, Map<String, Component>> PlayerKeys, Map<Integer, Map<String, Boolean>> HUDPropMap) {
		ActiveLevel = activeLevel;
		playerComponentsforLevel = PlayerKeys.get(ActiveLevel);
		listOfStatusLabels = HUDPropMap.get(ActiveLevel); 
		approvedLabels = extractSelectedLabels(listOfStatusLabels);
		gameStatusFactory = new HUDFactory(); //factory for all the labels
		labelsToDisplay = gameStatusFactory.create(approvedLabels);
		toolbarLayout = new HBox(250);
		for (IGameStatusLabel l: labelsToDisplay) {
			toolbarLayout.getChildren().add((Label) l);
		}
		//constructor to create a Sample Tool Bar
		this.getItems().add(toolbarLayout);
	}	
	
	private List<String> extractSelectedLabels(Map<String, Boolean> listOfStatusLabels){
		List<String> approvedLabels = new ArrayList<String>();
		for (String s: listOfStatusLabels.keySet()) {
			if (listOfStatusLabels.get(s)) {
				approvedLabels.add(s);
			}
		}
		return approvedLabels;
	}
	
	/**
	 * Update the gameState Values
	 */
	public void updateGameStatusLabels() {
		for (IGameStatusLabel label : labelsToDisplay) {
		label.update(label.extractGameStateValue(playerComponentsforLevel));
		}
		}
	
	
	public void updateGameStatusValues(Map<Integer, Map<String, Component>> playerKeys) {
		playerComponentsforLevel = playerKeys.get(ActiveLevel);
	}
	

}
