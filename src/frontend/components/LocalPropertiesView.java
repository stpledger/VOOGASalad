package frontend.components;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import engine.components.Component;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.function.Consumer;
import java.lang.reflect.Constructor;

/**
 * Opens up the Local Properties window so that an editor can edit certain features of an entity,
 * such as poison, health, velocity, etc. 
 * @author Collin Brown (cdb55)
 * @author Dylan Powers (ddp19)
 *
 */
public class LocalPropertiesView extends PropertiesView {
	
	private final String PROPERTIES_PACKAGE = "resources/components";
	private final String COMPONENT_PREFIX = "engine.components.";
	private int entityNumber;
	private Button submitButton;
	
	/**
	 * Initialize the object with a given broadcast method
	 * @param entityNumber
	 */
	public LocalPropertiesView(int entityNumber) {
		super();
		this.entityNumber = entityNumber;
		this.fill();
	}
	
	/**
	 * Fills the window with the appropriate text boxes and listeners so that the broadcast can tell the highest level that something has changed.
	 */
	@Override
	protected void fill() {
		int currentRow = 0;
		for (String property : ResourceBundle.getBundle(PROPERTIES_PACKAGE).keySet()) {
			getRoot().add(new ComponentForm(this.entityNumber, property, numFields(property)), 0, currentRow++);
		}
	}
	
	/**
	 * Gets the title for the window.
	 * @return the title for this window.
	 */
	@Override
	public String title() {
		return String.format("Entity %d Local Properties", this.entityNumber);
	}

	/**
	 * Given a name of a component class in the engine, find the number of fields that it takes and their types.
	 * This is required for generating the appropriate amount of text boxes
	 * @param component the String name of the component in need of identification
	 */
	private int numFields(String component) {
		// strip dashes which may have been there due to user-friendliness
		String fullName = COMPONENT_PREFIX + component.replace("-",  "");
		try {
			Class clazz = Class.forName(fullName);
			Constructor cons = clazz.getDeclaredConstructors()[0];
			return cons.getParameterCount();
		} catch (ClassNotFoundException e) {
			// TODO better exception
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Class " + component + " does not exist.");
			a.showAndWait();
		}
		return 0;
	}
	
}
