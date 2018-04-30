package authoring.factories;

import java.util.Properties;

/**
 * Handles the creation of a NumberField through the use of the ElementFactory. 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class NumberFieldElement extends NumberField implements Element{
	private String myOriginalName;
	public NumberFieldElement(String text) {
		super();
		this.handleText(text);
		this.myOriginalName = text;
	}

	@Override
	public void handleText(String text) {
		this.setPromptText(text);
	}

	@Override
	public void setLanguage(Properties language) {
		this.handleText(language.getProperty(this.myOriginalName, this.myOriginalName));
	}

}
