package authoring.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public abstract class AbstractComponentForm extends GridPane {
	protected static final String COMPONENT_PREFIX = "engine.components.";
	protected String name;
	protected int numFields;
	protected List<TextField> fields = new ArrayList<>();
	protected List<Label> labels = new ArrayList<>();
	
	protected final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	protected Properties language = new Properties();
	
	protected abstract Object buildComponent();

	/**
	 * Check if the user fully entered something in this form. If the form has multiple text boxes, then
	 * the user should have input two separate things. If not, the program should alert them of this.
	 * @return true if the user fully entered everything that they should enter
	 */
	protected boolean validComponent() {
		for (TextField tf : this.fields) {
			if (tf.getText() == null || tf.getText().trim().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Casts the {@code String} in the {@code TextField} to the appropriate class based upon the types
	 * declared in the constructor.
	 * @param desiredType the type that the {@code String} should be cast to
	 * @param text the text to be cast
	 * @return an Object representing the casted class
	 */
	protected Object cast(Class<?> desiredType, String text) {
		Object reflectValue;
		if (desiredType.equals(double.class)) {
			reflectValue = Double.parseDouble(text);
		}
		else {
			reflectValue = text;
		}
		return reflectValue;
	}

}
