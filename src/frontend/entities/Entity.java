package frontend.entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engine.components.Component;
import engine.support.ComponentBuilder;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Hemanth Yakkali
 * @author Dylan Powers
 *
 */
public abstract class Entity extends ImageView{

	/**
	 * Unique ID to the entity
	 */
    private int ID;
    
    /**
     * List of components which define the entity
     */
    private List<Component> components;
    
    /**
     * The constructor simply sets the ID of the entity and initializes its list of components
     * @param ID which identifies an entity
    **/
    public Entity (int ID) {
        this.ID = ID;
        components = new ArrayList<>();
        
    }
    
    /**
     * Adds components that are inherent to the specific entity.
     */
    public abstract void addDefaultComponents();

    /**
     * 
     * @param c Component object
     */
    public void add(Component c) {
        components.add(c);
    }
    
    /**
     * 
     * @param c Component object
     */
    public void remove (Component c) {
        components.remove(c);
    }
    
    /**
     * Sets health, because every entity should always have health.
     * @param health
     */
	public void setHealth(double health) {
		this.add(ComponentBuilder.buildComponent(this.getID(), "Health", Arrays.asList(new String[] {Double.toString(health)})));
	}
	
	/**
	 * 
	 * @param filename File path of the sprite image
	 * @throws FileNotFoundException
	 */
	public void setSprite(String filename) throws FileNotFoundException {
		this.add(ComponentBuilder.buildComponent(this.getID(), "Sprite", Arrays.asList(new String[] {filename})));
	}
	
	/**
	 * 
	 * @param width Width of entity
	 * @param height Height of entity
	 */
	public void setDimension(double width, double height) {
		this.add(ComponentBuilder.buildComponent(this.getID(), "Dimension", Arrays.asList(new String[] {Double.toString(width),Double.toString(height)})));
	}
	
	/**
	 * 
	 * @param x X position
	 * @param y Y position
	 */
	public void setPosition(double x, double y) {
		this.add(ComponentBuilder.buildComponent(this.getID(), "Position", Arrays.asList(new String[] {Double.toString(x),Double.toString(y)})));
	}
	
	/**
	 * 
	 * @param type Type of entity
	 */
	public void setEntityType(String type) {
		this.add(ComponentBuilder.buildComponent(this.getID(), "EntityType", Arrays.asList(new String[] {type})));
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
     * @return List of components which define the entity
     */
    public List<Component> getComponentList(){
    	return this.components;
    }

}
