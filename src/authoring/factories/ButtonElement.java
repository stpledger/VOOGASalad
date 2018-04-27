package authoring.factories;

import java.util.Properties;
import java.util.function.Consumer;

import javafx.scene.control.Button;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class ButtonElement extends Button implements ClickableElement{
	private String myOriginalName;

	public ButtonElement(String text) {
		super();
		this.handleText(text);
		this.myOriginalName = text;
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
		this.handleText(language.getProperty(this.myOriginalName, this.myOriginalName));
	}

}
