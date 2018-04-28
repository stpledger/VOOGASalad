package authoring.entities.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import authoring.forms.ComponentForm;
import authoring.forms.EntityComponentForm;

public class EntityBuilderData {
	
	private Map<Class, Object[]> componentAttributes = new HashMap<>();
	
	private final static String COMPONENT_PREFIX = "engine.components.";
	
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
		if(componentAttributes.containsKey(c)) {
			componentAttributes.remove(c);
		}
		componentAttributes.put(c, new Object[] {val});
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
		}
	}
	

	/**
	 * Saves an entity
	 * @param list
	 * @throws Exception
	 */
	public void save(List<ComponentForm> list) throws Exception  {
		for(ComponentForm componentForm : list) {
			Object[] tempArr = (Object[]) componentForm.buildComponent();
			if(tempArr != null) {
				componentAttributes.put(Class.forName(COMPONENT_PREFIX + componentForm.getName()), tempArr);
			}
		}
		
		EntitySaver saver = new EntitySaver();
		
		saver.writeXML(componentAttributes,(String) this.componentAttributes.get(engine.components.Name.class)[0]);
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
