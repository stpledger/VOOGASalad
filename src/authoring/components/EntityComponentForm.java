package authoring.components;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EntityComponentForm extends AbstractComponentForm{
	

	/**
	 * Constructs the form with the given name and number of fields necessary, as determined by reflection.
	 * @param name the name of the component
	 * @param numFields the number of fields necessary for this component
	 */
	public EntityComponentForm(String name) {
		this.name = name;
		fields = new ArrayList<>();
		int col = 0;
		Label label = new Label(name);
		label.getStyleClass().add("component-form-label");
		col++;
		this.add(label, col, 0);
		this.numFields = getNumFields(name);
		for (int i = 0; i < (numFields-1); i++) {
			TextField tf = new TextField();
			tf.getStyleClass().add("component-text-field");
			fields.add(tf);
			col++;
			this.add(tf, col, 0);
		}
	}

	/**
	 * Builds the necessary Object Array to build a component based upon the data that is inside of the text fields.
	 * Should be performed only when the user clicks the submit button.
	 * @return a component that accurately represents the data in this wrapper class
	 */
	public Object[] buildComponent() {
		if (!validComponent()) return null;
		String fullName =  COMPONENT_PREFIX + this.name;
		Object[] params = new Object[fields.size()];
		try {
			Class<?> clazz = Class.forName(fullName);
			Constructor<?> cons = clazz.getDeclaredConstructors()[0];
			Class<?>[] types = cons.getParameterTypes();
			for (int i = 0; i < types.length-1; i++) {
				String text = fields.get(i).getText();
				params[i] = cast(types[i+1], text);
			}
			return params;
		} catch (Exception e) {
			LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
		}
		return null;
	}

	public String getName() {
		return this.name;
	} 
}
