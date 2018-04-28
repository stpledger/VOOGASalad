package authoring.factories;

import java.util.Properties;

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
		this.setId(text);
	}

	@Override
	public void handleText(String text) {
		this.setPromptText(text);
	}
	
	@Override
	public void setLanguage(Properties language) {
		this.handleText(language.getProperty(this.getId(),this.getId()));
	}

}
