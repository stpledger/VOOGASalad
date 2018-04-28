package authoring.entities;

import engine.components.Component;

/**
 * Class to define entities that cannot be interacted with.
 * @author dylanpowers
 *
 */
public abstract class NonInteractableEntity extends Entity {

	public NonInteractableEntity(int ID) {
		super(ID);
	}

	@Override
	protected void add(Component c) {
		// TODO Auto-generated method stub

	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

}
