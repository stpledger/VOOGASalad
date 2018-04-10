package frontend.entities;

import engine.components.Damage;
import engine.components.Position;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class Enemy extends Entity{
	
	private final String TYPE = "Enemy";
	private final double ENEMY_DAMAGE = 10;
	private final double ENEMY_LIFETIME = 50;
	private final double ENEMY_HEALTH = 100;
	
	public Enemy(int ID) {
		super(ID);
		addDefaultComponents();
	}

	@Override
	public void addDefaultComponents() {
		this.setHealth(ENEMY_HEALTH);
		this.setDamage(ENEMY_DAMAGE, ENEMY_LIFETIME);
		this.setEntityType(TYPE);
	}
	
	public void setVelocity(double xVel, double yVel) {
		this.add(new Position(this.getID(),xVel,yVel));
	}
	
	public void setDamage(double damage, double lifetime) {
		this.add(new Damage(this.getID(),damage,lifetime));
	}

}
