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

    public Component get (String name) {
        return components.get(name);
    }

    public boolean contains (String name) {
        return components.containsKey(name);
    }

    public void add (String name, Component c) {
        components.put(name, c);


    public void remove (Component c) {
        components.remove(c);
    }
    
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
    
    public abstract void addDefaultComponents();
}
