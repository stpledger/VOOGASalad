package authoring.entities.componentbuilders;

import engine.components.Component;
import engine.components.DataComponent;
import engine.components.SingleDataComponent;
import engine.components.StringComponent;

import java.lang.reflect.Constructor;
import java.util.logging.Logger;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 * Class to build a sprite from a given image path
 * @author dylanpowers
 *
 */
public class ComponentBuilder {

	private final static String FOLDER_PATH = "data/";
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	
	public Component build(int ID, Element e) {
		try {
			Class<?> clazz = Class.forName("engine.components." + e.getNodeName());
			Constructor<?> cons = clazz.getDeclaredConstructors()[0];
			Component component;
			System.out.println(clazz.getSuperclass());
			if (SingleDataComponent.class.isAssignableFrom(clazz)) {
				System.out.println("Probs bout to throw an exception");
				component = (Component) cons.newInstance(ID, Double.valueOf(e.getTextContent()));
			} else {
				component = (Component) cons.newInstance(ID, e.getTextContent());
			}
			return component;
		} catch (Exception e2) {
			LOGGER.log(java.util.logging.Level.SEVERE, e2.toString(), e2);
			e2.printStackTrace();
			return null;
		}
		
	}

}
