package authoring.forms;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Properties;

import authoring.entities.data.PackageExplorer;
import authoring.factories.Element;
import authoring.factories.ElementFactory;
import authoring.factories.ElementType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EntityComponentForm extends AbstractComponentForm  implements ComponentForm {
	
	ElementFactory eFactory = new ElementFactory();
	ArrayList<Element> elements = new ArrayList<>();
	/**
	 * Constructs the form with the given name and number of fields necessary, as determined by reflection.
	 * @param name the name of the component
	 * @param numFields the number of fields necessary for this component
	 * @throws Exception 
	 */
	public EntityComponentForm(String name) throws Exception {
		this.name = name;
		int col = 0;
		Label label = (Label) eFactory.buildElement(ElementType.Label, name);
		label.getStyleClass().add("component-form-label");
		col++;
		this.add(label, col, 0);
		this.numFields = PackageExplorer.getNumFields(name);
		elements.add((Element) label);
		for (int i = 0; i < (numFields-1); i++) {
			TextField tf = (TextField) eFactory.buildElement(ElementType.TextField, "text");
			tf.getStyleClass().add("component-text-field");
			elements.add((Element) tf);
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
		if (!validComponent()) { 
			return null;
		}
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

	public void setLanguage(Properties lang) {
		language = lang;
		for(Element e: elements) {
			e.setLanguage(language);
		}
	}
}
