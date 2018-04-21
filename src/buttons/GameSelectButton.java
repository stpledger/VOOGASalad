package buttons;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GameSelectButton extends Button {

	private File gameFile;
	
	public GameSelectButton(String name, File file) {
		this.setText(name);
		gameFile = file;
		this.setGameSelectEvent();
	}
	
	private void setGameSelectEvent() {
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				//Change Scene to go to game player screen and set File.
				
			}
		});
	
	
}
