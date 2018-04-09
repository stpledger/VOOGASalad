package entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.List;


import java.util.HashMap;

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

    public boolean contains (Component component) {
        return components.contains(component);
    }

    public void add (Component c) {
        components.add(c);
    }


    public void remove (Component c) {
        components.remove(c);
    }
    
    public int getID() {
    		return this.ID;
    }
    
    /**
     * Sets health, because every entity should always have health.
     * @param health
     */
	public void setHealth(double health) {
		this.add();
	}
	
	public void setSprite(String filename) throws FileNotFoundException {
		this.add();
	}
	
	public void setDimension(double width, double height) {
		this.add(new Dimension(this.getID(),width,height));
	}
	
	public void setPosition(double x, double y) {
		this.add(new Position(this.getID(),x,y));
	}
	
	
    
}
