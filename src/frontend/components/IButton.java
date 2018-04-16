package frontend.components;

import java.util.function.Consumer;

import javafx.scene.control.Button;

public interface IButton {
	
	public Button makeElement(String text, Consumer<Void> event);

}
