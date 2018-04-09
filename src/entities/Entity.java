package entities;

import java.util.Map;

//import gameEngine.ECS.components.IComponent;

import java.util.HashMap;

import engine.components.Component;

/**
 * Entity class
 **/
public class Entity {

    private double ID; //unique ID to an entity
    private Map<String, Component> components; //list of components which define the entity

    /**
     * The constructor simply sets the ID of the entity and initializes its list of components
     * @param ID which identifies an entity
    **/
    public Entity (double ID) {
        this.ID = ID;
        components = new HashMap<>();
    }

    public double getID () { return ID; }

    public Component get (String name) {
        return components.get(name);
    }

    public boolean contains (String name) {
        return components.containsKey(name);
    }

    public void add (String name, Component c) {
        components.put(name, c);
    }

    public void remove (Component c) {
        components.remove(c);
    }
}
