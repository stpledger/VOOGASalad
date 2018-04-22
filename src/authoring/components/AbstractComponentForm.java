package authoring.components;

import java.lang.reflect.Constructor;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public abstract class AbstractComponentForm extends GridPane {
	protected final String COMPONENT_PREFIX = "engine.components.";
	protected String name;
	protected int numFields;
	protected List<TextField> fields;

	protected abstract Object buildComponent();

	/**
	 * Check if the user fully entered something in this form. If the form has multiple text boxes, then
	 * the user should have input two separate things. If not, the program should alert them of this.
	 * @return true if the user fully entered everything that they should enter
	 */
	protected boolean validComponent() {
		for (TextField tf : this.fields) {
			if (tf.getText() == null || tf.getText().trim().isEmpty())
				return false;
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
		if (desiredType.equals(double.class))
			reflectValue = Double.parseDouble(text);
		else
			reflectValue = text;
		return reflectValue;
	}



	/**
	 * Given a name of a component class in the engine, find the number of fields that it takes and their types.
	 * This is required for generating the appropriate amount of text boxes
	 * @param component the String name of the component in need of identification
	 */
	protected int getNumFields(String component) {
		// strip dashes which may have been there due to user-friendliness
		String fullName = COMPONENT_PREFIX + component.replace("-",  "");
		try {
			Class clazz = Class.forName(fullName);
			Constructor cons = clazz.getDeclaredConstructors()[0];
			// subtract one because the first parameter is ALWAYS the parent ID of the entity
			int prop = cons.getParameterCount() - 1;
			return cons.getParameterCount();
		} catch (ClassNotFoundException e) {
			// TODO better exception
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Class " + component + " does not exist.");
			e.printStackTrace();
			a.showAndWait();
		}
		return 0;
	}
}
