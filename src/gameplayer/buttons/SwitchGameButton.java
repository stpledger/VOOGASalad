package gameplayer.buttons;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;

public class SwitchGameButton extends Button implements IGamePlayerButton{
	private final String BUTTON_NAME = "Switch Games";
	private BooleanProperty switchBoolean;
	
	public SwitchGameButton() {
		this.setText(BUTTON_NAME);
		switchBoolean = new SimpleBooleanProperty(false);
		this.setEvent();
	}

	public void setEvent() {
		this.setOnAction(e-> setSwitchBoolean());
		// TODO Auto-generated method stub
		
	}
	
	public BooleanProperty getSwitchBooleanProperty() {
		return switchBoolean;
	}
	
	public void setSwitchBoolean() {
		switchBoolean.setValue(!switchBoolean.getValue());
	}


}
