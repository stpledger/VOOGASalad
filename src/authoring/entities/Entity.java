package authoring.entities;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import authoring.views.properties.LocalPropertiesView;

import engine.components.Component;
import engine.components.EntityType;
import engine.components.Health;
import engine.components.Sprite;
import engine.components.groups.Damage;
import engine.components.groups.Dimension;
import engine.components.groups.Position;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 * @author Dylan Powers
 * @author Collin Brown
 *
 */

public abstract class Entity extends ImageView implements Serializable {

	public final static int ENTITY_WIDTH = 50;
	public final static int ENTITY_HEIGHT = 50;
	
	protected final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
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
		this.setFitWidth(ENTITY_WIDTH);
		this.setFitHeight(ENTITY_HEIGHT);
		Consumer<List<Component>> onSubmit = componentsToAdd -> {
			for (Component c : componentsToAdd) {
				this.add(c);
			}
		};
		this.setOnMouseClicked(e -> {
			if(e.getClickCount()==2) {
				LocalPropertiesView LPV = new LocalPropertiesView(this, onSubmit);
				LPV.open();
			}
			e.consume();
		});
		this.setOnMouseDragged(e -> {
			this.setPosition(e.getX() + this.getLayoutX() - this.getFitWidth()/2, e.getY() + this.getLayoutY() - this.getFitHeight()/2);
			e.consume();
		});
		addDefaultComponents();
	}


	/**
	 * Adds components that are inherent to the specific entity.
	 */
	protected abstract void addDefaultComponents();

	/**
	 * Gets the names of all of the components.
	 * @return the names of all of the components
	 *
	 */
	public List<String> getNames() {
		List<String> ans = new ArrayList<>();
		for (Component c : this.components) {
			ans.add(c.getKey());
		}
		return ans;
	}
	/**
	 * 
	 * @param c Component object
	 */
	public void add(Component c) {
		if (c != null) {

			if (this.contains(c)) {
				this.removeByName(c.getKey());
			}

			this.components.add(c);
		}
	}
	
	/**
	 * Adds all the components in the list to the entity
	 * @param componentList List<Component>
	 */
	public void addAll(List<Component> componentList) {
		for(Component c: componentList) {
			this.add(c);
		}
	}

	/**
	 * 
	 * @param c Component object
	 */
	public void remove (Component c) {
		components.remove(c);
	}

	/**
	 * Remove a component based upon its String value.
	 * @param name the name of the component to remove
	 */
	private void removeByName(String name) {
		for (Component c : this.components) {
			if (c.getKey().equals(name)) {
				this.remove(c);
				return;
			}
		}
	}

	/**
	 * Checks (by name) if the current entity already contains a given component.
	 * @param c the component to check
	 * @return true iff the component already belongs to this entity
	 */
	private boolean contains(Component c) {
		for (Component existing : this.components) {

			if (existing.getKey() == c.getKey()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks (explicitly) by name if the current entity already contains this component.
	 * @param name the name of the component to check
	 * @return true iff the component already belongs to this entity
	 */
	public boolean contains(String name) {
		for (Component existing : this.components) {
			if (existing.getKey().equals(name)) {
				return true;
			}
		}
		return false;
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
		this.setWidth((int) width);
		this.setHeight((int) height);
	}
	
	private void setWidth(int cells) {
		this.setFitWidth(cells*Entity.ENTITY_WIDTH);
	}
	
	private void setHeight(int cells) {
		this.setFitHeight(cells*Entity.ENTITY_HEIGHT);
	}

	/**
	 * 
	 * @param x X position
	 * @param y Y position
	 */
	public void setPosition(double x, double y) {
		this.removeByName("Position");
		this.add(new Position(this.getID(), x, y));
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
	 * Get a component by name.
	 * @param name the name of the component needed
	 * @return component defined by this string name
	 */
	public Component get(String name) {
		for (Component c : this.components) {
			if (c.getKey().equals(name)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * @return type of this entity
	 */
	public abstract String type();
	
	/**
	 * @return the name of this entity
	 */
	public abstract String name();

	/**
	 * 
	 * @return List of components which define the entity
	 */

	public List<Component> getComponentList(){
		return this.components;
	}

}
