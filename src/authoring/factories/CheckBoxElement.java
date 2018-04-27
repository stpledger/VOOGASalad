package authoring.factories;

import java.util.Properties;

import javafx.scene.control.CheckBox;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class CheckBoxElement extends CheckBox implements Element{
	private String myOriginalName;

	public CheckBoxElement(String text) {
		super();
		this.handleText(text );
		this.myOriginalName = text;
	}

	@Override
	public void handleText(String text) {
		this.setText(text);	
	}
	
	@Override
	public void setLanguage(Properties language) {
		this.handleText(language.getProperty(this.myOriginalName, this.myOriginalName));
	}

}
