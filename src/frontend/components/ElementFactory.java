package frontend.components;

import java.util.function.Consumer;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class ElementFactory {
	
//	private Button button;
//	private TextField textField;
//	private NumberField numField;
//	private MenuItem mItem;
	
	public ElementFactory() {
//		switch (eType) {
//			case BUTTON:
//				PropertiesButton 
//				break;
//			case MENUITEM:
//				makeMenuItem(text, event);
//				break;
//			case TEXTFIELD:
//				makeTextField(text, event);
//				break;
//			case NUMBERFIELD:
//				makeNumberField(text, event);
//				break;
//		}
	}
	
	public <T> T makeElement(String text) {
		Button button = new Button();
		return (T) button;
	}

}
