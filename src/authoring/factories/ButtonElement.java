package authoring.factories;

import javafx.scene.control.Button;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
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
