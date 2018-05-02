package GamePlayer.menu;

import GamePlayer.buttons.RestartButton;
import GamePlayer.buttons.SaveGameButton;
import GamePlayer.buttons.SwitchGameButton;
import GamePlayer.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PauseMenu extends Popup {
	
	private SaveGameButton saveBtn;
	private RestartButton restartBtn;
	public SwitchGameButton switchBtn;
	private Controller gamePlayerController;
	private Stage myStage;
	/**
	 * Constructor for the Pause Menu Popup
	 */
	public PauseMenu(Stage stage) {
		myStage = stage;
		VBox pane = new VBox();
		saveBtn = new SaveGameButton(this);
		switchBtn = new SwitchGameButton(stage);
		restartBtn = new RestartButton(gamePlayerController);
		pane.setSpacing(10);
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(new Label("Paused"), restartBtn, switchBtn, saveBtn);
		this.getContent().add(pane);
		pane.getStylesheets().add("gameplayer/playstyle.css");
		pane.setFillWidth(true);
	}

	public void saveGame(){
		gamePlayerController.saveGame();
	}
}
