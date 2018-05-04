package authoring.entities;

import engine.components.Collidable;
import engine.components.Height;
import engine.components.Width;

/**
 * Background class that acts as a preset. Makes it easier to users to create an enemy without needing 
 * to manually add components.
 * @author Hemanth Yakkali(hy115)
 */
public class Background extends NonInteractableEntity{

	private static final String TYPE = "Background";

	public Background(int ID, String name) {
		super(ID);
		this.setName(name);
		this.setPresetType(TYPE);
		this.addDefaultComponents();
	}
	
	private void addDefaultComponents() {
		System.out.println("Adding!!!!");
		Width width = new Width(this.getID(),Entity.ENTITY_WIDTH*3);
		Height height = new Height(this.getID(),Entity.ENTITY_HEIGHT*3);
		this.add(height);
		this.add(width);
	}

}
