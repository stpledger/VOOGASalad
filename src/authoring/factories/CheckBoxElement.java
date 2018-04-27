package authoring.factories;

import java.util.Properties;

import javafx.scene.control.CheckBox;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 * @author Collin Brown(cdb55)
 *
 */
public class CheckBoxElement extends CheckBox implements Element{

	public CheckBoxElement(String text) {
		super();
		this.handleText(text );
		this.setId(text);
	}

	@Override
	public void handleText(String text) {
		this.setText(text);	
	}
	
	@Override
	public void setLanguage(Properties language) {
		this.handleText(language.getProperty(this.getId()));
	}

}
