package authoring.forms;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Properties;

import authoring.entities.data.PackageExplorer;
import authoring.factories.Element;
import authoring.factories.ElementFactory;
import authoring.factories.ElementType;
import engine.components.Component;
import engine.components.SingleDataComponent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * A wrapper class for forms that contains the label name and the text fields necessary, and can build components upon completion.
 * @author dylanpowers
 *
 */
public class PropertiesComponentForm extends AbstractComponentForm implements ComponentForm {
	private int entity;

	/**
	 * Constructs the form with the given name and number of fields necessary, as determined by reflection.
	 * @param entity the entity that the component should be added to
	 * @param name the name of the component
	 * @param numFields the number of fields necessary for this component
	 */
	public PropertiesComponentForm(int eID, String name) throws Exception {
		super(name);
		entity = eID;
	}
	/**
	 * Constructs the form with the given name and number of fields necessary, as determined by reflection.
	 * @param name the name of the component
	 * @param existingValue the String form of the existing value of this component
	 * @throws Exception 
	 */
	public PropertiesComponentForm(int entity, String name, String existingValue) throws Exception {
		this(entity, name);
		this.field.setText(existingValue);
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
		Object[] params = new Object[2];
		// first argument is always the entity ID
		params[0] = this.entity;
		try {
			Class<?> clazz = Class.forName(fullName);
			Constructor<?> cons = clazz.getDeclaredConstructors()[0];
			if (SingleDataComponent.class.isAssignableFrom(clazz)) {
				params[1] = Double.valueOf(this.field.getText());
			} else {
				params[1] = this.field.getText();
			}
			Component comp = (Component) cons.newInstance(params);
			return comp;
		} catch (Exception e) {
			LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
			return null;
		}
	}

	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public void setLanguage(Properties language) {
		// TODO Auto-generated method stub

	}
}
