package frontend.factories;

import javafx.scene.control.Button;

public class ButtonElement extends Button implements Element{
	
	public ButtonElement(String text) {
		super();
		this.handleText(text);
	}

	@Override
	public void handleText(String text) {
		this.setText(text);
	}
	
	
}
