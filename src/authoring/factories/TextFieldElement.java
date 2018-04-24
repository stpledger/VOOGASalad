package authoring.factories;

import javafx.scene.control.TextField;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class TextFieldElement extends TextField implements Element{

	public TextFieldElement(String text) {
		super();
		this.handleText(text);
	}

	@Override
	public void handleText(String text) {
		this.setPromptText(text);
	}

}
