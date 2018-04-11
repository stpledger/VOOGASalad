package frontend.components;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import engine.components.*;
import frontend.entities.Entity;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
/**
 * A wrapper class for forms that contains the label name and the text fields necessary, and can build components upon completion.
 * @author dylanpowers
 *
 */
public class ComponentForm extends GridPane {

	private final String COMPONENT_PREFIX = "engine.components.";
	private int entity;
	private String name;
	private int numFields;
	private List<TextField> fields;
	/**
	 * Constructs the form with the given name and number of fields necessary, as determined by reflection.
	 * @param entity the entity that the component should be added to
	 * @param name the name of the component
	 * @param numFields the number of fields necessary for this component
	 */
	public ComponentForm(int entity, String name, int numFields) {
		this.entity = entity;
		this.name = name;
		fields = new ArrayList<>();
		int col = 0;
		this.add(new Label(name), col++, 0);
		for (int i = 0; i < (numFields-1); i++) {
			TextField tf = new TextField();
			fields.add(tf);
			this.add(tf, col++, 0);
		}
	}
	
	/**
	 * Builds the necessary component based upon the data that is inside of the text fields.
	 * Should be performed only when the user clicks the submit button.
	 * @return a component that accurately represents the data in this wrapper class
	 */
	public Component buildComponent() {
		if (!validComponent()) return null;
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
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException  | IllegalArgumentException | InvocationTargetException e) {
			// TODO better exception
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Check if the user fully entered something in this form. If the form has multiple text boxes, then
	 * the user should have input two separate things. If not, the program should alert them of this.
	 * @return true if the user fully entered everything that they should enter
	 */
	private boolean validComponent() {
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
	private Object cast(Class<?> desiredType, String text) {
		Object reflectValue;
		if (desiredType.equals(double.class))
			reflectValue = Double.parseDouble(text);
		else if (desiredType.equals(int.class))
			reflectValue = Integer.parseInt(text);
		else
			reflectValue = text;
		return reflectValue;
	}
}
