package entities;

import java.util.ArrayList;
import java.util.List;
import engine.components.Component;
/**
 * Entity class
 */
public abstract class Entity {

    private int ID; //unique ID to an entity
    private List<Component> components; //list of components which define the entity
    /**
     * The constructor simply sets the ID of the entity and initializes its list of components
     * @param ID which identifies an entity
     */
    public Entity(int ID) {
        this.ID = ID;
        components = new ArrayList<>();
    }
    
    public abstract void addDefaultComponents();

    public int getID() { 
    		return ID; 
    	}

    public boolean contains(Component component) {
        return components.contains(component);
    }

    public void add(Component component) {
        components.add(component);
    }

    public void remove(Component c) {
        components.remove(c);
    }
}
