package authoring.entities;
import engine.components.Jumps;
import engine.components.Lives;
import engine.components.Player;
import javafx.scene.input.KeyCode;
import engine.components.Score;
import engine.components.presets.HeightPowerUpCollision;
import engine.components.presets.PlayerCollision;
import engine.components.presets.PlayerMovement;

/**
 * A class to represent the player object, and its default components.
 * @author Dylan Powers
 * @author Hemanth Yakkali(hy115)
 */
public class HeightPowerUp extends InteractableEntity {

	private final static String TYPE = "HeightPowerUp";
	private final static double INITIAL_HEALTH = 100;
	private final static int INITIAL_LIVES = 10;
	
	/**
	 * Construct the object with a given ID
	 * @param ID the ID of the parent object
	 */
	public HeightPowerUp(int ID, String name) {
		super(ID);
		addDefaultComponents();
		this.setName(name);
		this.setPresetType(TYPE);
	}

	/**
	 * Add the default components to the player object.
	 */
	private void addDefaultComponents() {		
		this.setEntityType(TYPE);
		
		this.add(new HeightPowerUpCollision(this.getID()));
		
	}

}