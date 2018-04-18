package authoring.entities;
/**
 * A class to represent the player object, and its default components.
 * @author Dylan Powers
 * @author Hemanth Yakkali(hy115)
 */
public class Player extends Entity {

	private final String TYPE = "Player";
	private final double INITIAL_HEALTH = 100;
	private final double INITIAL_DAMAGE = 10;
	private final double INITIAL_LIFETIME = 20;
	private final double PLAYER_WIDTH = 25;
	private final double PLAYER_HEIGHT = 50;
	
	/**
	 * Construct the object with the given ID.
	 * @param ID the ID of this object.
	 */
	public Player(int ID) {
		super(ID);
	}

	/**
	 * Add the default components to the player object.
	 */
	@Override
	public void addDefaultComponents() {
		this.setHealth(INITIAL_HEALTH);
		this.setEntityType(TYPE);
		this.setDimension(PLAYER_WIDTH, PLAYER_HEIGHT);
		this.setDamage(INITIAL_DAMAGE,INITIAL_LIFETIME);
		
		// TODO add method to set sprite
	}

	@Override
	public String type() {
		return TYPE;
	}
}