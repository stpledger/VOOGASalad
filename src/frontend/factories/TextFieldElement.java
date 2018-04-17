package frontend.factories;

import java.util.function.Consumer;

import javafx.scene.control.TextField;

public class TextFieldElement extends TextField implements Element{
	
	public TextFieldElement(String text) {
		super();
		this.setPromptText(text);
	}

	@Override
	public void addEvent(Consumer<Void> event) {}

}
