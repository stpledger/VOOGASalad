package authoring.entities;

import java.util.ArrayList;
import java.util.ResourceBundle;

import authoring.exceptions.AuthoringAlert;
import authoring.exceptions.AuthoringException;
import engine.components.Component;

/**
 * Class to define entities that cannot be interacted with.
 * This class should only have components to represent its picture, position, and size.
 * @author Dylan Powers
 *
 */
public abstract class NonInteractableEntity extends Entity {

	private final static String NI_PROPERTIES_PATH = "resources.Entities/NonInteractable";
	private final String ERROR_MESSAGE = "Cannot add component %s to a non-interactable object.";
	/**
	 * Construct the entity with the given ID
	 * @param ID
	 */
	public NonInteractableEntity(int ID) {
		super(ID);
		this.components = new ArrayList<>();
		this.setInteractable(false);
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
			throw new AuthoringException(ERROR_MESSAGE, AuthoringAlert.SHOW, c.getKey());
		}
	}

}
