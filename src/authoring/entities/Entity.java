package authoring.entities;

import java.io.FileNotFoundException;

import engine.components.Component;
import engine.components.Height;
import engine.components.Sprite;
import engine.components.Width;
import engine.components.XPosition;
import engine.components.YPosition;
import javafx.scene.image.ImageView;

/**
 * Super class to represent the top level of the entity chain. 
 * @author Dylan Powers
 */
public abstract class Entity extends ImageView {
	
	private int ID;
	public final static int ENTITY_WIDTH = 50;
	public final static int ENTITY_HEIGHT = 50;
	
	/**
	 * Set the ID of this entity
	 * @param ID the ID of this entity
	 */
	public Entity(int ID) {
		this.ID = ID;
	}
	
	/**
	 * 
	 * @return Unique ID of the entity
	 */
	public int getID() {
		return this.ID;
	}
	
	/**
	 * 
	 * @param filename File path of the sprite image
	 * @throws FileNotFoundException
	 */
	protected void setSprite(String filename) throws FileNotFoundException {
		this.add(new Sprite(this.getID(),filename));
	}
	
	/**
	 * 
	 * @param x X position
	 * @param y Y position
	 */
	public void setPosition(double x, double y) {
		this.add(new XPosition(this.getID(), x));
		this.add(new YPosition(this.getID(), y));
		this.setLayoutX(x);
		this.setLayoutY(y);
	}

	/**
	 * 
	 * @param width Width of entity
	 * @param height Height of entity
	 */
	protected void setDimension(double width, double height) {
		this.add(new Width(this.getID(),width));
		this.add(new Height(this.getID(),height));
	}
	
	/**
	 * Add a given component to this entity. 
	 * @param component
	 */
	protected abstract void add(Component c);
	

	/**
	 * @return type of this entity
	 */
	public abstract String type();
	
	/**
	 * @return the name of this entity
	 */
	public abstract String name();
	

}
