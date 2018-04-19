package authoring.factories;

import javafx.scene.control.CheckBox;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class CheckBoxElement extends CheckBox implements Element{

	public CheckBoxElement(String text) {
		super();
		this.handleText(text);
	}

	@Override
	public void handleText(String text) {
		this.setText(text);	
	}

}
