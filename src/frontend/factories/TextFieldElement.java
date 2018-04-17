package frontend.factories;

import javafx.scene.control.TextField;

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
