package buttons;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Button that changes to the initial scene to play a new game
 * @author Ryan Fu
 */
public class NewGameButton extends Button{
	
	private final String BUTTON_NAME = "New Game"; //change to a resource file
	
	public NewGameButton() {
		this.setText(BUTTON_NAME);
		this.setNewGameEvent();
	}
	/*
	 * private method to set the action event to speed
	 */
	private void setNewGameEvent() {
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				newGame(); //activate the file upload method
			}
		});
	}
	
	/**
	 * Changes scene to initial intro scene.
	 */
	private void newGame() {
}
}
