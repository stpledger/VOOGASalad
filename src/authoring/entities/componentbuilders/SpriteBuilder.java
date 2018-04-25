package authoring.entities.componentbuilders;

import engine.components.Component;
import engine.components.Sprite;

import java.io.FileNotFoundException;
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
public class SpriteBuilder implements ComponentBuilder {

	private final static String FOLDER_PATH = "data/";
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public SpriteBuilder() {
		//
	}
	@Override
	public Component build(int ID, Element e) {
		// only one element, take the first one
		try {
			System.out.println("Trying to create " + e.getNodeName());
			Class<?> clazz = Class.forName("engine.components." + e.getNodeName());
			Constructor<?> cons = clazz.getDeclaredConstructors()[0];
			Component c = (Component) cons.newInstance(ID, e.getTextContent());
			System.out.println("Component " + c + " created.");
		} catch (Exception e2) {
			LOGGER.log(java.util.logging.Level.SEVERE, e2.toString(), e2);
			e2.printStackTrace();
		}
		return null;
	}

}
