package authoring.factories;

import java.util.Properties;

import javafx.scene.control.Label;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 * @author Collin Brown(cdb55)
 *
 */
public class LabelElement extends Label implements Element{
	String myOriginalName;

	public LabelElement(String text) {
		myOriginalName = text;
		this.handleText(text);
	}

	@Override
	public void handleText(String text) {
		this.setText(text);
	}

	@Override
	public void setLanguage(Properties language) {
		this.handleText(language.getProperty(myOriginalName));
	}

}
