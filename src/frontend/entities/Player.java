package frontend.entities;

/**
 * A class to represent the player object, and its default components.
 * @author Dylan Powers
 *
 */
public class Player extends Entity {

	private final String TYPE = "Player";
	private final double INITIAL_HEALTH = 100;
	private final double INITIAL_DAMAGE = 10;
	
	/**
	 * Construct the object with the given ID.
	 * @param ID the ID of this object.
	 */
	public Player(int ID) {
		super(ID);
		addDefaultComponents();
	}

	/**
	 * Add the default components to the player object.
	 */
	@Override
	public void addDefaultComponents() {
		this.setEntityType(TYPE);
		this.setHealth(INITIAL_HEALTH);
	}

}
