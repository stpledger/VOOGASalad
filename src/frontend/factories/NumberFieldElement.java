package frontend.factories;

import frontend.components.NumberField;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
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
