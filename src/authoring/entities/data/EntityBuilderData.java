package authoring.entities.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import authoring.exceptions.AuthoringAlert;
import authoring.exceptions.AuthoringException;
import authoring.forms.ComponentForm;

public class EntityBuilderData {

	private Map<Class, Object[]> componentAttributes = new HashMap<>();

	private final static String COMPONENT_PREFIX = "engine.components.";
	private final String NAME_ERROR_MESSAGE = "There must be a value in the \"Name\" field.\n";
	private final String COMPONENT_ERROR_MESSAGE = "The component %s does not exist. Please check the package engine.components to make sure that the component has been created.\n";
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public EntityBuilderData() {

	}

	/**
	 * Add a component to the entityBuilderData
	 * @param c Class of component to be created
	 * @param val value of said component
	 */
	public void setComponent(Class c, String val) {
		try {
			if (componentAttributes.containsKey(c)) {
				componentAttributes.remove(c);
			}
			componentAttributes.put(c, new Object[] {val});
		} catch (Exception e) {
			throw new AuthoringException(COMPONENT_ERROR_MESSAGE, AuthoringAlert.SHOW, c.getName());
		}
	}


	/**
	 * Saves an entity
	 * @param list
	 * @throws Exception
	 */
	public void save(List<ComponentForm> list) throws Exception {
		for(ComponentForm componentForm : list) {
			Object[] tempArr = (Object[]) componentForm.buildComponent();
			if(tempArr != null) {
				try {
					componentAttributes.put(Class.forName(COMPONENT_PREFIX + componentForm.getName()), tempArr);
				} catch (Exception e) {
					throw new AuthoringException(COMPONENT_ERROR_MESSAGE, AuthoringAlert.SHOW, componentForm.getName());
				}
			}
		}
		EntitySaver saver = new EntitySaver();
		if (this.componentAttributes.get(engine.components.Name.class)[0] == null) {
			throw new AuthoringException(NAME_ERROR_MESSAGE, AuthoringAlert.SHOW);
		}
		saver.writeXML(componentAttributes, (String) this.componentAttributes.get(engine.components.Name.class)[0]);
	}

	/**
	 * Gets the type of the entity being created
	 * @return String Entity type to be created
	 */
	public String getType() {
		return (String) this.componentAttributes.get(engine.components.Type.class)[0];
	}

	/**
	 * Returns a map<Class, Object[]> that represents all the components of a new entity
	 * @return map<Class, Object[]>
	 */
	public Map<Class, Object[]> getComponentAttributes() {
		return componentAttributes;
	}

}
