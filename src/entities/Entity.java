package entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engine.components.Component;
import engine.support.ComponentBuilder;

/**
 * 
 * @author Hemanth Yakkali
 * @author Dylan Powers
 *
 */
public abstract class Entity {

    private int ID; //unique ID to an entity
    private List<Component> components; //list of components which define the entity
    /**
     * The constructor simply sets the ID of the entity and initializes its list of components
     * @param ID which identifies an entity
    **/
    public Entity (int ID) {
        this.ID = ID;
        components = new ArrayList<>();
    }

    public void add(Component c) {
        components.add(c);
    }
    
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
	
	public void setSprite(String filename) throws FileNotFoundException {
		this.add(ComponentBuilder.buildComponent(this.getID(), "Sprite", Arrays.asList(new String[] {filename})));
	}
	
	public void setDimension(double width, double height) {
		this.add(ComponentBuilder.buildComponent(this.getID(), "Dimension", Arrays.asList(new String[] {Double.toString(width),Double.toString(height)})));
	}
	
	public void setPosition(double x, double y) {
		this.add(ComponentBuilder.buildComponent(this.getID(), "Position", Arrays.asList(new String[] {Double.toString(x),Double.toString(y)})));
	}
	    
    public abstract void addDefaultComponents();
    
    public int getID() {
    	return this.ID;
    }
    
    public List<Component> getComponentList(){
    	return components;
    }

}
