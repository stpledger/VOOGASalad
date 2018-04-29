package authoring.entities;

import engine.components.KeyInput;
import engine.components.Lives;
import engine.components.Player;
import engine.components.Score;
import javafx.scene.input.KeyCode;

/**
 * A class to represent the player object, and its default components.
 * @author Dylan Powers
 * @author Hemanth Yakkali(hy115)
 */
public class User extends InteractableEntity {

	private final static String TYPE = "Player";
	private String name;
	private final static double INITIAL_HEALTH = 100;
	private final static int INITIAL_LIVES = 10;
	/**
	 * Construct the object with a given ID
	 * @param ID the ID of the parent object
	 */
	public User(int ID, String name) {
		super(ID);
		addDefaultComponents();
		this.name = name;
	}

	/**
	 * Add the default components to the player object.
	 */
	public void addDefaultComponents() {
		this.setHealth(INITIAL_HEALTH);
		this.setEntityType(TYPE);
		this.setDimension(Entity.ENTITY_WIDTH, Entity.ENTITY_HEIGHT);
		this.add(new Player(this.getID()));
		this.add(new Lives(this.getID(), INITIAL_LIVES));
		this.add(new Score(this.getID(), 0));
	}

	@Override
	public String type() {
		return TYPE;
	}
	
	@Override
	public String name() {
		return this.name;
	}
}