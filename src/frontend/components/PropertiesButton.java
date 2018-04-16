package frontend.components;

import java.util.function.Consumer;

import javafx.scene.control.Button;

public class PropertiesButton implements IButton{

	@Override
	public Button makeButton(String text, Consumer<Void> event) {
		Button button = new Button(text);
		button.setOnAction(e->event.accept(null));
		return button;
	}

}
