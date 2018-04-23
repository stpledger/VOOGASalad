package authoring.factories;

import javafx.scene.control.Tab;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class TabElement extends Tab implements Element{
	
	public TabElement(String text) {
		super();
		this.handleText(text);
	}

	@Override
	public void handleText(String text) {
		this.setText(text);
	}

}
