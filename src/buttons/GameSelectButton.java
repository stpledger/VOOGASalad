package buttons;

import java.io.File;

import data.DataGameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameSelectButton extends Button {

	private DataGameState gameState;
	
	/**
	 * 
	 * @param name of the Game
	 * @param file of the GameData to be imported
	 * @param image of the Game to used for the button.
	 */
	public GameSelectButton(String name, DataGameState gameState, Image image) {
		this.setText(name);
		this.setGraphic(new ImageView(image));
		gameState = gameState;
		this.setGameSelectEvent();
	}
	
	private void setGameSelectEvent() {
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				//Change Scene to go to game player screen and set File.
				
			}
		});
	
	
	}
}
