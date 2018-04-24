package authoring.entities.componentbuilders;

import engine.components.Component;
import engine.components.Sprite;

import java.io.FileNotFoundException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 * Class to build a sprite from a given image path
 * @author dylanpowers
 *
 */
public class SpriteBuilder implements ComponentBuilder {

	private final String FOLDER_PATH = "data/";

	public SpriteBuilder() {}
	@Override
	public Component build(int ID, Element e) {
		NodeList nList = e.getElementsByTagName("Sprite");
		// only one element, take the first one
		Element e1 = (Element) nList.item(0);
		try {
			return new Sprite(ID, FOLDER_PATH + e1.getTextContent());
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (DOMException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return null;
	}

}
