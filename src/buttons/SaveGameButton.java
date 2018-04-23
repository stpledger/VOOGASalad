package buttons;


import GamePlayer.GamePlayerController;
import Menu.PauseMenu;
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
	private PauseMenu pauseMenu;

	
	public SaveGameButton(PauseMenu p) {
		pauseMenu = p;
		this.setText(BUTTON_NAME);
		this.setOnAction(e -> saveGame());
	}

	//save the current state of the Game
	private void saveGame() {
       pauseMenu.saveGame();
	}
	

}
