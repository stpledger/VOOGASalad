package authoring.entities;

import engine.components.Player;

/**
 * A class to represent the player object, and its default components.
 * @author Dylan Powers
 * @author Hemanth Yakkali(hy115)
 */
public class User extends Entity {

	private final static String TYPE = "Player";
	private String name;
	private final static double INITIAL_HEALTH = 100;
	private final static int INITIAL_LIVES = 10;
	private final static double PLAYER_WIDTH = 25;
	private final static double PLAYER_HEIGHT = 50;
	/**
	 * Construct the object with a given ID
	 * @param ID the ID of the parent object
	 */
	public User(int ID) {
		super(ID);
		addDefaultComponents();
	}

	/**
	 * Add the default components to the player object.
	 */
	@Override
	public void addDefaultComponents() {
		this.setHealth(INITIAL_HEALTH);
		this.setEntityType(TYPE);
		this.setDimension(PLAYER_WIDTH, PLAYER_HEIGHT);
		this.add(new Player(this.getID(), INITIAL_LIVES));
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