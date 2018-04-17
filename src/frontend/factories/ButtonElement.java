package frontend.factories;

import java.util.function.Consumer;

import javafx.scene.control.Button;

public class ButtonElement extends Button implements Element{
	
	public ButtonElement(String text) {
		super();
		this.setText(text);
	}
	
	@Override
	public void addEvent(Consumer<Void> event) {
		this.setOnAction(e->event.accept(null));
	}
	
}
