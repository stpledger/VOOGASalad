package authoring.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import engine.components.Component;

/**
 * Class to define entities that cannot be interacted with.
 * This class should only have components to represent its picture, position, and size.
 * @author Dylan Powers
 *
 */
public abstract class NonInteractableEntity extends Entity {

	private final static String NI_PROPERTIES_PATH = "resources.Entities/NonInteractable";
	private final static String TYPE = "Noninteractable";
	List<Component> components;
	/**
	 * Construct the entity with the given ID
	 * @param ID
	 */
	public NonInteractableEntity(int ID) {
		super(ID);
		components = new ArrayList<>();
		this.type = TYPE;
	}

	/**
	 * Add a component ONLY if it represents either position, dimension, or sprite.
	 * @param c the component to add
	 */
	@Override
	public void add(Component c) {
		if (ResourceBundle.getBundle(NI_PROPERTIES_PATH).keySet().contains(c.getKey())) {
			components.add(c);
		} else {
			// TODO there should be an alert pop up here
		}
	}

	/**
	 * @return the type of this entity
	 */
	public abstract String type();

	/**
	 * @return the name of this entity
	 */
	public abstract String name();

}
