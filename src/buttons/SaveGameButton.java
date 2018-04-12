package buttons;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


/**
 * Button that saves the current state of the game
 * @author Ryan
 *
 */
public class SaveGameButton extends Button{
	
	private final String BUTTON_NAME = "Save Game"; //change to a resource file
	
	public SaveGameButton() {
		this.setText(BUTTON_NAME);
		this.setSaveEvent();
	}
	/*
	 * private method to set the action event to speed
	 */
	private void setSaveEvent() {
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				saveGame(); //activate the file upload method
			}
		});
	}

	//save the current state of the Game
	private void saveGame() {

       
	}
	

}
