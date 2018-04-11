package frontend.entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import engine.components.Component;
import frontend.components.LocalPropertiesView;
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
    public Entity(int ID) {
        this.ID = ID;
        components = new ArrayList<>();
        Consumer<List<Component>> addComponents = (componentsToAdd) -> {
        		for (Component c : componentsToAdd) {
        			this.add(c);
        		}
        };
        this.setOnMouseClicked(e -> {
        		LocalPropertiesView LPV = new LocalPropertiesView(addComponents);
        		LPV.open();
        }); 
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
    		for (Component other : this.components) {
    			// Don't add duplicates
    			if (c.getKey().equals(other.getKey())) {
    				this.components.remove(other);
    				break;
    			}
    		}
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
	public void setHealth(double health) {
		this.add(new Health(this.getID(),health));
	}
	
	/**
	 * 
	 * @param filename File path of the sprite image
	 * @throws FileNotFoundException
	 */
	public void setSprite(String filename) throws FileNotFoundException {
		this.add(new Sprite(this.getID(),filename));
	}
	
	/**
	 * 
	 * @param width Width of entity
	 * @param height Height of entity
	 */
	public void setDimension(double width, double height) {
		this.add(new Dimension(this.getID(),width,height));
	}
	
	/**
	 * 
	 * @param x X position
	 * @param y Y position
	 */
	public void setPosition(double x, double y) {
		this.add(new Position(this.getID(),x,y));
	}
	
	/**
	 * 
	 * @param type Type of entity
	 */
	public void setEntityType(String type) {
		this.add(new EntityType(this.getID(),type));
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
