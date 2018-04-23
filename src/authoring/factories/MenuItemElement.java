package authoring.factories;

import java.util.function.Consumer;

import javafx.scene.control.MenuItem;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class MenuItemElement extends MenuItem implements ClickableElement{

	public MenuItemElement(String text) {
		this.handleText(text);
	}

	@Override
	public void handleText(String text) {
		this.setText(text);
	}

	@Override
	public void handleConsumer(Consumer<Void> event) {
		this.setOnAction(e->event.accept(null));
	}

}
