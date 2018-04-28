package authoring.factories;

import java.util.Properties;
import java.util.function.Consumer;

import javafx.scene.control.Button;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 * @author Collin Brown(cdb55)
 *
 */
public class ButtonElement extends Button implements ClickableElement{

	public ButtonElement(String text) {
		super();
		this.handleText(text);
		this.setId(text);
	}

	@Override
	public void handleText(String text) {
		this.setText(text);
	}

	@Override
	public void handleConsumer(Consumer<Void> event) {
		this.setOnAction(e->event.accept(null));
	}
	
	@Override
	public void setLanguage(Properties language) {
		this.handleText(language.getProperty(this.getId(), this.getId()));
	}

}
