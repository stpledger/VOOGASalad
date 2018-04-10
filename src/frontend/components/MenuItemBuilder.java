package frontend.components;

import java.util.function.Consumer;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * 
 * @author Hemanth Yakkali
 *
 */
public class MenuItemBuilder {
	
	public static Button buildButton(String text, Consumer<Void> event) {
		Button button = new Button(text);
		button.setOnAction(e->event.accept(null));
		return button;
	}
	
	public static MenuItem buildMenuItem(String text, Consumer<Void> event) {
		MenuItem menuItem = new MenuItem(text);
		menuItem.setOnAction(e->event.accept(null));
		return menuItem;
	}
	
	public static TextField buildTextField(String text, Consumer<Void> event, boolean isNumber) {
		if(isNumber) {
			NumberField field = new NumberField();
			field.setOnAction(e->event.accept(null));
			return field;
		} else {
			TextField field = new TextField(text);
			field.setOnAction(e->event.accept(null));
			return field;
		}
	}
	
}
