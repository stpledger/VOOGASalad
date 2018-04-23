package Menu;

import GamePlayer.GamePlayerController;
import buttons.FileUploadButton;
import buttons.RestartButton;
import buttons.SaveGameButton;
import buttons.SwitchGameButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

public class PauseMenu extends Popup {
	
	private SaveGameButton saveBtn;
	private RestartButton restartBtn;
	public SwitchGameButton switchBtn;
	private GamePlayerController gamePlayerController;
	/**
	 * Constructor for the Pause Menu Popup
	 */
	public PauseMenu(GamePlayerController g) {
		gamePlayerController = g;
		VBox pane = new VBox();
		saveBtn = new SaveGameButton(this);
		switchBtn = new SwitchGameButton();
		restartBtn = new RestartButton(gamePlayerController);
		pane.setSpacing(10);
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(new Label("Paused"), restartBtn, switchBtn, saveBtn);
		this.getContent().add(pane);
		pane.getStylesheets().add("./GamePlayer/playstyle.css");
		pane.setFillWidth(true);
	}

	public void saveGame(){
		gamePlayerController.saveGame();
	}
}
