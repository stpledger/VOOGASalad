package gameplayer.buttons;

import data.DataGameState;
import data.DataRead;
import gameplayer.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javax.xml.crypto.Data;

public class RestartButton extends Button{

	private final String BUTTON_NAME = "Restart Game";
	private Controller gameController;
	
	public RestartButton(Controller g) {
		gameController = g;
		this.setText(BUTTON_NAME);
		this.setRestartEvent();
		
	}
	
	
	private void setRestartEvent() {
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				DataGameState dg = DataRead.copyGame();
				System.out.print(dg.getGameName());
			}
		});
	}
}
