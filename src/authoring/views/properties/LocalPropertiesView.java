package authoring.views.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import engine.components.Component;
import java.util.function.Consumer;
import java.util.logging.Logger;

import authoring.entities.Entity;
import authoring.forms.ComponentForm;
import authoring.forms.PropertiesComponentFormCollection;

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
	
	private PropertiesComponentFormCollection componentFormCollection;
	
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

	@Override
	protected void fill() {
		componentFormCollection = new PropertiesComponentFormCollection(entity.getID(), new String[] {""}, e-> {save((List<Component>) e);});
		this.getRoot().getChildren().add(componentFormCollection);
		componentFormCollection.setLanguage(language);
		componentFormCollection.fill(this.type);
		this.getRoot().getScene().getWindow().sizeToScene();
		
	}
	
	private void save(List<Component> componentsToAdd) {
		onSubmit.accept(componentsToAdd);
		this.makeAlert(this.title() + " has been updated!");
		this.close();
	}

}
