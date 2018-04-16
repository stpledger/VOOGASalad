package frontend.components;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import engine.components.Component;
import frontend.entities.Entity;
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
	
	private final String PROPERTIES_PACKAGE = "resources.menus.Entity/";
	private final String COMPONENT_PREFIX = "engine.components.";
	private final String SUBMIT_TEXT = "Submit Changes";
	private List<PropertiesComponentForm> activeForms;
	private Consumer<List<Component>> onSubmit;
	private int entityID;
	private String type;
	
	/**
	 * Initialize the object with a given broadcast method
	 * @param entityNumber
	 */
	public LocalPropertiesView(int entityID, String type, Consumer<List<Component>> onSubmit) {
		super();
		this.entityID = entityID;
		this.type = type;
		this.onSubmit = onSubmit;
		this.fill();
	}
	
	/**
	 * Fills the window with the appropriate text boxes and listeners so that the broadcast can tell the highest level that something has changed.
	 */
	@Override
	protected void fill() {
		int currentRow = 0;
		this.activeForms = new ArrayList<>();
		for (String property : ResourceBundle.getBundle(PROPERTIES_PACKAGE + type).keySet()) {
			PropertiesComponentForm cf = new PropertiesComponentForm(this.entityID, property);
			this.activeForms.add(cf);
			getRoot().add(cf, 0, currentRow++);
		}
		Button submit = new Button(SUBMIT_TEXT);
		submit.setOnAction(e -> {
			List<Component> componentsToAdd = new ArrayList<>();
			for (PropertiesComponentForm cf : this.activeForms) {
				componentsToAdd.add(cf.buildComponent());
			}
			onSubmit.accept(componentsToAdd);
			this.close();
		});
		getRoot().add(submit, 0, currentRow);
	}
	
	/**
	 * Gets the title for the window.
	 * @return the title for this window.
	 */
	@Override
	public String title() {
		return String.format("Entity %d Local Properties", this.entityID);
	}
	
}
