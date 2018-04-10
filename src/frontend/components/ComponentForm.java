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
		for (int i = 0; i < numFields; i++) {
			TextField tf = new TextField();
			this.add(tf, col++, 0);
		}
	}
	
	/**
	 * Builds the necessary component based upon the data that is inside of the text fields.
	 * Should be performed only when the user clicks the submit button.
	 * @return a component that accurately represents the data in this wrapper class
	 */
	public Component buildComponent() {
		String fullName =  COMPONENT_PREFIX + this.name;
		Object[] params = new Object[numFields];
		for (int i = 0; i < numFields; i++) {
			params[i] = fields.get(i).getText();
		}
		try {
			Class clazz = Class.forName(fullName);
			Constructor cons = clazz.getDeclaredConstructors()[0];
			return (Component) cons.newInstance(this.entity, params);
		} catch (ClassNotFoundException e) {
			// TODO better exception
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
