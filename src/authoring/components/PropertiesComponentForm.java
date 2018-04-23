package authoring.components;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Map;

import engine.components.Component;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * A wrapper class for forms that contains the label name and the text fields necessary, and can build components upon completion.
 * @author dylanpowers
 *
 */
public class PropertiesComponentForm extends AbstractComponentForm {

	private static final String PROP_FILE = "authoring.properties.Components.properties";
	private int entity;
	/**
	 * Constructs the form with the given name and number of fields necessary, as determined by reflection.
	 * @param entity the entity that the component should be added to
	 * @param name the name of the component
	 * @param numFields the number of fields necessary for this component
	 */
	public PropertiesComponentForm(int entity, String name) {
		this.entity = entity;
		this.name = name;
		fields = new ArrayList<>();
		int col = 0;
		col++;
		this.add(new Label(name), col , 0);
		this.numFields = getNumFields(name);
		for (int i = 0; i < (numFields-1); i++) {
			TextField tf = new TextField();
			fields.add(tf);
			col++;
			this.add(tf, col, 0);
		}
	}
	/**
	 * Constructs the form with the given name and number of fields necessary, as determined by reflection.
	 * @param name the name of the component
	 * @param numFields the number of fields necessary for this component
	 */
	public PropertiesComponentForm(int entity, String name, Map<String, String> existingValues) {
		this(entity, name);
		int index = 0;
		for (String param : existingValues.keySet()) {
			System.out.println("Value " + index + " is " + param + " for key " + existingValues.get(param));
			index++;
			fields.get(index).setText(existingValues.get(param));
		}
	}

	/**
	 * Builds the necessary component based upon the data that is inside of the text fields.
	 * Should be performed only when the user clicks the submit button.
	 * @return a component that accurately represents the data in this wrapper class
	 */
	public Component buildComponent() {
		if (!validComponent()) {
			return null;
		}
		String fullName =  COMPONENT_PREFIX + this.name;
		Object[] params = new Object[fields.size() + 1];
		// first argument is always the entity ID
		params[0] = this.entity;
		try {
			Class<?> clazz = Class.forName(fullName);
			Constructor<?> cons = clazz.getDeclaredConstructors()[0];
			Class<?>[] types = cons.getParameterTypes();
			for (int i = 1; i < types.length; i++) {
				String text = fields.get(i-1).getText();
				params[i] = cast(types[i], text);
			}
			Component comp = (Component) cons.newInstance(params);
			System.out.println(comp);
			return comp;
		} catch (Exception e) {
			LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
		}
		return null;
	}
}
