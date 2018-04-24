package authoring.factories;

import javafx.scene.control.Label;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class LabelElement extends Label implements Element{

	public LabelElement(String text) {
		this.handleText(text);
	}

	@Override
	public void handleText(String text) {
		this.setText(text);
	}

}
