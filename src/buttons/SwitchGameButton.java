package buttons;

import GamePlayer.GamePlayerController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SwitchGameButton extends Button{
	private final String BUTTON_NAME = "Switch Games";
	private BooleanProperty switchBoolean;
	
	public SwitchGameButton() {
		this.setText(BUTTON_NAME);
		this.setSwitchEvent();
		switchBoolean = new SimpleBooleanProperty(false);
		
	}
	
	private void setSwitchEvent() {
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				setSwitchBoolean();
			}
		});
	}
	
	public BooleanProperty getSwitchBooleanProperty() {
		return switchBoolean;
	}
	
	public void setSwitchBoolean() {
		switchBoolean.setValue(!switchBoolean.getValue());
	}

}
