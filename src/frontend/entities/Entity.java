package frontend.entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import frontend.components.LocalPropertiesView;
import engine.components.Component;
import engine.components.Damage;
import engine.components.Dimension;
import engine.components.EntityType;
import engine.components.Health;
import engine.components.Position;
import engine.components.Sprite;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

/**
 * 
 * @author Hemanth Yakkali
 * @author Dylan Powers
 *
 */

public abstract class Entity extends ImageView {

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
    public Entity(int ID) {
        this.ID = ID;
        components = new ArrayList<>();
        Consumer<List<Component>> addComponents = (componentsToAdd) -> {
        		for (Component c : componentsToAdd) {
        			this.add(c);
        		}
        };
        this.setOnMouseClicked(e -> {
        		if (e.getButton().equals(MouseButton.SECONDARY)) {
        			LocalPropertiesView LPV = new LocalPropertiesView(addComponents, this.type(), this.getID());
        			LPV.open();
        		}
        }); 
    }
    /**
     * This is a constructor that does not set the id of an entity;
     */
    public Entity() {
    	
    }
    
    /**
     * Adds components that are inherent to the specific entity.
     */
    protected abstract void addDefaultComponents();
   
    /**
     * 
     * @param c Component object
     */
    public void add(Component c) {
//    		for (Component other : this.components) {
//    			// Don't add duplicates
//    			if (c.getKey().equals(other.getKey())) {
//    				this.components.remove(other);
//    				break;
//    			}
//    		}
    		this.components.add(c);
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
    protected void setHealth(double health) {
		this.add(new Health(this.getID(),health));
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
	 * @param width Width of entity
	 * @param height Height of entity
	 */
    protected void setDimension(double width, double height) {
		this.add(new Dimension(this.getID(),width,height));
	}
	
	/**
	 * 
	 * @param x X position
	 * @param y Y position
	 */
    public void setPosition(double x, double y) {
		this.add(new Position(this.getID(),x,y));
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
	
	/**
	 * 
	 * @param type Type of entity
	 */
    protected void setEntityType(String type) {
		this.add(new EntityType(this.getID(),type));
	}
	
	/**
	 * 
	 * @param damage Double damage 
	 * @param lifetime Double lifetime
	 */
    protected void setDamage(double damage, double lifetime) {
		this.add(new Damage(this.getID(),damage,lifetime));
	}
	        
	/**
	 * 
	 * @return Unique ID of the entity
	 */
    public int getID() {
    		return this.ID;
    }
    
    /**
     * @return type of this entity
     */
    public abstract String type();
    
    /**
     * 
     * @return List of components which define the entity
     */

    public List<Component> getComponentList(){
    		return this.components;
    }

}
