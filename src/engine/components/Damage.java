package engine.components;


import java.util.ArrayList;
import java.util.List;

/**
 * This component defines poisons points of the game object.
 * The entity with this component can harm other entities it collides with,
 * by reducing poisons points from the health points of others.
 * It is instantiated with an initial value passed from authoring environment,
 * and changes according to game logic.
 * @author Yameng
 */
public class Damage extends Component {
	private double damage;
	private double lifetime;
	public static String KEY = "Damage";


<<<<<<< HEAD
	public Damage(int pid, double damage, double lifetime) {
=======
	public Damage (int pid, double damage, double lifetime) {
>>>>>>> 9d2c61e58bf633d7fda5043bafe04e74d489d2b8
		super(pid, KEY);
		this.damage = damage;
		this.lifetime = lifetime;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getLifetime() {
		return lifetime;
	}

	public void decrementLife() {
		lifetime--;
	}
<<<<<<< HEAD
	
	public void setLifetime(double lifetime) {
		this.lifetime = lifetime;
	}
=======
>>>>>>> 9d2c61e58bf633d7fda5043bafe04e74d489d2b8

}
