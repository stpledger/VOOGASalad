package frontend.components;

import java.util.function.Consumer;

import javafx.scene.control.Button;

public class MenuItemBuilder {
	
	public static Button buildButton(String text, Consumer<Void> event) {
		Button button = new Button(text);
		button.setOnAction(e->event.accept(null));
		return button;
	}
	
}
