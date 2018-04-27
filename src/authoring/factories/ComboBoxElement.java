package authoring.factories;

import java.util.Properties;
import java.util.function.Consumer;

import javafx.scene.control.ComboBox;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class ComboBoxElement extends ComboBox implements ClickableElement {
	String myOriginalName;
	
	public ComboBoxElement(String text) {
		super();
		this.myOriginalName = text;
		handleText(text);
	}

	@Override
	public void handleText(String text) {
		this.setPromptText(text);
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
