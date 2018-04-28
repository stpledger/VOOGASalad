package authoring.entities.data;

import authoring.entities.Entity;
import authoring.entities.componentbuilders.ComponentBuilder;
import engine.components.Component;
import engine.components.Type;
import engine.components.XPosition;
import engine.components.YPosition;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

/**
 * Class to load an entity from an XML file when it is placed into the {@code GameEditorView}.
 * @author Dylan Powers
 *
 */
public class EntityLoader {
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private DocumentBuilder documentBuilder;
	private final String ERROR_MESSAGE = "The component %s is invalid.";
	private final String COMPONENT_WRAPPER = "Component";
	private final String ENTITY_PREFIX = "authoring.entities.";
	private final String COMPONENT_BUILDER = "authoring.entities.componentbuilders.";
	private final String XML_EXTENSION = ".xml";
	private final String DATA_PREFIX = "data/";
	private final String TYPE = Type.KEY;
	
	public EntityLoader() {
		try {
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			//LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
		}
	}
	
	/**
	 * Get and build an entity that is represented by a given XML File.
	 * @param entityName the name of the entity to pull the xml file for
	 * @param ID the ID of the entity to create
	 * @param type the type of entity to instantiate
	 * @return an Entity object represented by the xml file
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Entity buildEntity(int ID, String entityName, double x, double y) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException {
		Element root = getRootElement(new File(DATA_PREFIX + entityName + XML_EXTENSION));
		String type = extractTypeFromRoot(root);
		Entity entity = (Entity) Class.forName(ENTITY_PREFIX + type).getDeclaredConstructors()[0].newInstance(ID, type);
		NodeList nList = root.getChildNodes();
		List<Component> compsToAdd = new ArrayList<>();
		ComponentBuilder cb = new ComponentBuilder();
		for (int i = 0; i < nList.getLength(); i++) {
			Element e = (Element) nList.item(i);
			compsToAdd.add(cb.build(ID, e));
		}
		compsToAdd.add(new XPosition(ID,x));
		compsToAdd.add(new YPosition(ID,y));
		entity.addAll(compsToAdd);
		return entity;
	}	

	/**
	 * Get the root element of an xml file to parse
	 * @param XMLFile the xml file to parse
	 * @return the root element of the file
	 */
	private Element getRootElement(File XMLFile) {
		try {
			documentBuilder.reset();
			Document XMLDoc = documentBuilder.parse(XMLFile);
			return XMLDoc.getDocumentElement();
		} catch (Exception e) {
			//LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get the text value contained within an XML element.
	 * @param e the element to be queried
	 * @param name the name of the component to be searched
	 * @return the text value of this certain element
	 */
	private String getTextValue(Element e, String name) {
		System.out.println(e.getNodeName());
		NodeList nList = e.getElementsByTagName(name);
		if (nList != null && nList.getLength() > 0) {
            return nList.item(0).getTextContent();
        } else {
        		throw new XMLException(ERROR_MESSAGE, name);
        }
	}
	
	/**
	 * Uses reflection to get the correct component builder.
	 * @param name the name of the component to create using the builder
	 */
	private ComponentBuilder getComponentBuilder(String name) {
		try {
			Class<?> clazz = Class.forName(COMPONENT_BUILDER + name);
			Constructor<?> cons = clazz.getDeclaredConstructors()[0];
			return (ComponentBuilder) cons.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * Given the root of the xml document, extract the type of the entity, which is necessary to instantiate it.
	 * @param root the root of the xml document
	 * @return the type of the entity to be created
	 */
	private String extractTypeFromRoot(Element root) {
		return getTextValue(root, TYPE);
	}
}
