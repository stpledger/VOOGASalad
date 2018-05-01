package gameplayer.buttons;

import data.DataGameState;
import gameplayer.controller.Controller;
import gameplayer.menu.PauseMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RestartButton extends Button implements IGamePlayerButton{

	private final String BUTTON_NAME = "Restart Game";
	private Controller gameController;
	private Stage myStage;
	private PauseMenu pauseMenu;
	
	public RestartButton(Stage stage, Controller g, PauseMenu pausemenu) {
		pauseMenu = pausemenu;
		myStage = stage;
		gameController = g;
		this.setText(BUTTON_NAME);
		this.setEvent();
	}
	
	
	public void setEvent() {
		this.setOnAction(e->{
			System.out.println("blah");
			DataGameState initialGameState = gameController.getInitialGameState();
			Controller restartController = new Controller(myStage, initialGameState);
			myStage.setScene(restartController.getControllerScene());
			pauseMenu.hide();
		});
	}
}
