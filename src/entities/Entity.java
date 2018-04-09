package entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.List;

import engine.components.Component;
import engine.components.Dimension;
import engine.components.Health;
import engine.components.Position;
import engine.components.Sprite;

/**
 * Entity class
 **/
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
    
    public abstract void addDefaultComponents();
    public int getID () { return ID; }

    public void add (Component c) {
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
		this.add(new Health(this.getID(),health));
	}
	
	public void setSprite(String filename) throws FileNotFoundException {
		this.add(new Sprite(this.getID(),filename));
	}
	
	public void setDimension(double width, double height) {
		this.add(new Dimension(this.getID(),width,height));
	}
	
	public void setPosition(double x, double y) {
		this.add(new Position(this.getID(),x,y));
	}
    
}
