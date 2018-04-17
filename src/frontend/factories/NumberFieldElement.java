package frontend.factories;

import frontend.components.NumberField;

public class NumberFieldElement extends NumberField implements Element{
	
	public NumberFieldElement(String text) {
		super();
		this.handleText(text);
	}

	@Override
	public void handleText(String text) {
		this.setPromptText(text);
	}
	
}
