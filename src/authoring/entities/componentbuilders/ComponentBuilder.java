package authoring.entities.componentbuilders;

import engine.components.Component;
import org.w3c.dom.Element;

/**
 * Interface that all component builders should implement. This defines certain behaviors that we would expect all builders to have.
 * @author Dylan Powers
 *
 */
public interface ComponentBuilder {

	/**
	 * Builds a component based upon a given {@code Element} from an XML file.
	 * @param e the element from an XML file
	 * @param ID the ID of the entity being created
	 * @return a Component represented by the given element
	 */
	public Component build(int ID, Element e);
}
