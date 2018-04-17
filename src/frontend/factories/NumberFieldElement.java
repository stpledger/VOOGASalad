package frontend.factories;

import java.util.function.Consumer;

import frontend.components.NumberField;

public class NumberFieldElement extends NumberField implements Element{
	
	public NumberFieldElement(String text) {
		super();
		this.setPromptText(text);
	}

	@Override
	public void addEvent(Consumer<Void> event) {
		this.setOnAction(e->event.accept(null));
	}
	
}
