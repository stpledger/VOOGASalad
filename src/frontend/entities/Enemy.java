package frontend.entities;

import java.util.Arrays;

import engine.support.ComponentBuilder;

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
		this.add(ComponentBuilder.buildComponent(this.getID(), "Position", Arrays.asList(new String[] {Double.toString(xVel),Double.toString(yVel)})));
	}
	
	public void setDamage(double damage, double lifetime) {
		this.add(ComponentBuilder.buildComponent(this.getID(), "Damage", Arrays.asList(new String[] {Double.toString(damage),Double.toString(lifetime)})));
	}

}
