package gameplayer.buttons;

import gameplayer.view.SplashScreenView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SwitchGameButton extends Button implements IGamePlayerButton{
	private final String BUTTON_NAME = "Switch Games";
	private Stage myStage;
	
	public SwitchGameButton(Stage stage) {
		myStage = stage;
		this.setText(BUTTON_NAME);
		this.setEvent();
	}

	public void setEvent() {
		this.setOnAction(e-> {
			SplashScreenView splashScreen = new SplashScreenView(myStage);
			myStage.setScene(splashScreen.getScene());
		});	
	}
	
}
