package frontend.elements;

import java.util.function.Consumer;

import javafx.scene.control.MenuItem;

public interface IMenuItem {

	public MenuItem makeMenuItem(String text, Consumer<Void> event);
	
}
