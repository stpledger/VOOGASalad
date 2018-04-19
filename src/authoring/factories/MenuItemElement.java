package authoring.factories;

import javafx.scene.control.MenuItem;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class MenuItemElement extends MenuItem implements Element{

	public MenuItemElement(String text) {
		this.handleText(text);
	}

	@Override
	public void handleText(String text) {
		this.setText(text);
	}

}
