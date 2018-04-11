package frontend.entities;

public class Enemy extends Entity {
	
	private final String TYPE = "Enemy";
	private final double INITIAL_HEALTH = 50;
	private final double INITIAL_DAMAGE = 5;
	private final double INITIAL_LIFETIME = 10;
	private final double ENEMY_WIDTH = 20;
	private final double ENEMY_HEIGHT = 40;

	public Enemy(int ID) {
		super(ID);
		addDefaultComponents();
	}

	@Override
	public void addDefaultComponents() {
		this.setHealth(INITIAL_HEALTH);
		this.setEntityType(TYPE);
		this.setDimension(ENEMY_WIDTH, ENEMY_HEIGHT);
		this.setDamage(INITIAL_DAMAGE, INITIAL_LIFETIME);
		
		//TODO add method to set sprite
	}

}
