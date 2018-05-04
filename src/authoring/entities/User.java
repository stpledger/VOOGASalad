package authoring.entities;

import engine.components.Animated;
import engine.components.Jumps;
import engine.components.Lives;
import engine.components.Player;
import javafx.scene.input.KeyCode;
import engine.components.Score;
import engine.components.XAcceleration;
import engine.components.XVelocity;
import engine.components.YAcceleration;
import engine.components.YVelocity;
//import engine.components.presets.BirdMovement;
import engine.components.presets.PlayerCollision;
import engine.components.presets.PlayerMovement;
import engine.components.presets.BottomCollision;

/**
 * A class to represent the player object, and its default components.
 * @author Dylan Powers
 * @author Hemanth Yakkali(hy115)
 */
public class User extends InteractableEntity {

	private final static String TYPE = "User";
	private final static double INITIAL_HEALTH = 100;
	private final static int INITIAL_LIVES = 3;
	private static final int GRAVITY = 160;
	
	/**
	 * Construct the object with a given ID
	 * @param ID the ID of the parent object
	 */
	public User(int ID, String name) {
		super(ID);
		addDefaultComponents();
		this.setName(name);
		this.setPresetType(TYPE);
	}

	/**
	 * Add the default components to the player object.
	 */
	private void addDefaultComponents() {
		this.setHealth(INITIAL_HEALTH);
		this.setEntityType(TYPE);
		this.add(new Player(this.getID()));
		this.add(new Lives(this.getID(), INITIAL_LIVES));
		this.add(new YAcceleration(this.getID(),GRAVITY));
		this.add(new XAcceleration(this.getID(), 50));
		this.add(new XVelocity(this.getID(), 0));
		this.add(new YVelocity(this.getID(), 0));
		this.add(new PlayerCollision(this.getID()));
		this.add(new Jumps(this.getID(), 3));
		this.add(new PlayerMovement(this.getID(),KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN));
		this.add(new Score(this.getID(), 0));
	}
}