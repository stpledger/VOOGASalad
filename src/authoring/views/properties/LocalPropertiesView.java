package authoring.views.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import engine.components.Component;
import engine.components.DataComponent;
import engine.components.StringComponent;
import javafx.scene.control.Button;
import java.util.function.Consumer;
import java.util.logging.Logger;

import authoring.entities.Entity;
import authoring.factories.ClickElementType;
import authoring.forms.PropertiesComponentForm;

/**
 * Opens up the Local Properties window so that an editor can edit certain features of an entity,
 * such as poison, health, velocity, etc. 
 * @author Collin Brown (cdb55)
 * @author Dylan Powers (ddp19)
 * @author Hemanth Yakkali(hy115)
 *
 */
public class LocalPropertiesView extends PropertiesView {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private final static String PROPERTIES_PACKAGE = "resources.menus.Entity/";
	private Consumer<List<Component>> onSubmit;
	private Entity entity;
	private String type;
	
	private Properties language = new Properties();

	/**
	 * Initialize the object with a given broadcast method
	 * @param entityNumber
	 */
	public LocalPropertiesView(Entity entity, Consumer<List<Component>> onSubmit) {
		super();
		this.entity = entity;
		this.type = entity.type();
		this.onSubmit = onSubmit;
	}

	/**
	 * Fills the window with the appropriate text boxes and listeners so that the broadcast can tell the highest level that something has changed.
	 */
	@Override
	protected void fill() {
		int currentRow = 0;
		List<PropertiesComponentForm> activeForms = new ArrayList<>();
		
		for (String property : ResourceBundle.getBundle(PROPERTIES_PACKAGE + type).keySet()) {
			PropertiesComponentForm cf;
			if (!entity.contains(property)) {
				cf = new PropertiesComponentForm(entity.getID(), property);
			} else {

				if (entity.get(property) instanceof DataComponent) {
					DataComponent dc = (DataComponent) entity.get(property);
					cf = new PropertiesComponentForm(entity.getID(), property, String.valueOf(dc.getData()));
				} else {
					StringComponent sc = (StringComponent) entity.get(property);
					cf = new PropertiesComponentForm(entity.getID(), property, sc.getData());
				}
			}
			activeForms.add(cf);
			currentRow++;
			getRoot().add(cf, 0, currentRow);
		}
		try {
			Button submit = (Button) this.getElementFactory().buildClickElement(ClickElementType.Button, this.getButtonBundle().getString("Submit"), e->{
				List<Component> componentsToAdd = new ArrayList<>();
				for (PropertiesComponentForm cf : activeForms) {
					componentsToAdd.add(cf.buildComponent());
				}
				onSubmit.accept(componentsToAdd);
				this.makeAlert(this.title() + " has been updated!");
				this.close();
			});
			getRoot().add(submit, 0, currentRow);
		} catch (Exception e1) {
			LOGGER.log(java.util.logging.Level.SEVERE, e1.toString(), e1);
		}
	}

	/**
	 * Gets the title for the window.
	 * @return the title for this window.
	 */
	@Override
	public String title() {
		return String.format("Entity %d Local Properties", this.entity.getID());
	}

	@Override
	public void setLanguage(Properties lang) {
		language = lang;
		
	}

}
