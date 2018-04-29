package buttons;

import gameplayer.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

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
				gameController.restartGame(); 
			}
		});
	}
}
