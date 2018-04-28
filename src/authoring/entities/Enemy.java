package authoring.entities;

/**
 *
 * @author Hemanth Yakkali(hy115)
 */
public class Enemy extends InteractableEntity {

	private final static String TYPE = "Enemy";
	private String name;
	private final static double INITIAL_HEALTH = 50;
	private final static double INITIAL_DAMAGE = 5;
	private final static double INITIAL_LIFETIME = 10;
	private final static double ENEMY_WIDTH = 20;
	private final static double ENEMY_HEIGHT = 40;

	public Enemy(int ID, String name) {
		super(ID);
		this.name = name;
		addDefaultComponents();
	}

	@Override
	public void addDefaultComponents() {
		this.setHealth(INITIAL_HEALTH);
		this.setEntityType(TYPE);
		this.setDimension(ENEMY_WIDTH, ENEMY_HEIGHT);
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
