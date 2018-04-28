package authoring.entities;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import authoring.views.properties.LocalPropertiesView;

import engine.components.Component;
import engine.components.DamageLifetime;
import engine.components.DamageValue;
import engine.components.EntityType;
import engine.components.Health;
import engine.components.Height;
import engine.components.Sprite;
import engine.components.Width;
import engine.components.XPosition;
import engine.components.YPosition;
import javafx.scene.image.ImageView;

/**
 * Class to define entities that can be interacted with.
 * @author Dylan Powers
 */
public abstract class InteractableEntity extends Entity {

	protected final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private List<Component> components;    

	/**
	 * The constructor simply sets the ID of the entity and initializes its list of components
	 * @param ID which identifies an entity
	 **/
	public InteractableEntity(int ID) {
		super(ID);
		components = new ArrayList<>();
		Consumer<List<Component>> onSubmit = componentsToAdd -> {
			for (Component c : componentsToAdd) {
				this.add(c);
			}
		};
		this.setOnMouseClicked(e -> {
			if(e.getClickCount() == 2) {
				LocalPropertiesView LPV = new LocalPropertiesView(this, onSubmit);
				LPV.open();
			}
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
	 * Adds a given component to the entity. Removes a component with the same key if it already exists.
	 * @param c Component object
	 */
	@Override
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
			if (existing.getKey().equals(c.getKey())) {
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
	 * 
	 * @return List of components which define the entity
	 */

	public List<Component> getComponentList(){
		return this.components;
	}

}
