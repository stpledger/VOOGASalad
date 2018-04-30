package gameplayer.menu;

import gameplayer.buttons.RestartButton;
import gameplayer.buttons.SaveGameButton;
import gameplayer.buttons.SwitchGameButton;
import gameplayer.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class PauseMenu extends Popup {
	
	private SaveGameButton saveBtn;
	private RestartButton restartBtn;
	public SwitchGameButton switchBtn;
	private Controller gamePlayerController;
	/**
	 * Constructor for the Pause Menu Popup
	 */
	public PauseMenu(Controller g) {
		gamePlayerController = g;
		VBox pane = new VBox();
		saveBtn = new SaveGameButton(this);
		switchBtn = new SwitchGameButton();
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
