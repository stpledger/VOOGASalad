package entities;

import java.util.Map;
import java.util.HashMap;

import components.IComponent;

/**
 * Entity class
 **/
public class Entity {

    private double ID; //unique ID to an entity
    private Map<String, IComponent> components; //list of components which define the entity

    /**
     * The constructor simply sets the ID of the entity and initializes its list of components
     * @param ID which identifies an entity
    **/
    public Entity (double ID) {
        this.ID = ID;
        components = new HashMap();
    }

    public double getID () { return ID; }

    public IComponent get (String name) {
        return components.get(name);
    }

    public boolean contains (String name) {
        return components.containsKey(name);
    }

    public void add (String name, IComponent c) {
        components.put(name, c);
    }

    public void remove (String name) {
        components.remove(name);
    }


}
