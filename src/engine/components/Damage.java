package engine.components;


import java.util.HashMap;
import java.util.Map;

/**
 * This component defines poisons points of the game object.
 *
 * The entity with this component is being harmed for the amount of time specified in  this class and 
 * for the amount of damage specified here as well.
 *
 * It is instantiated with an initial value passed from authoring environment,
 * and changes according to game logic.
 * @author Yameng
 */
public class Damage extends Component {
	private double damage;
	private double lifetime;
	public static String KEY = "Damage";


	public Damage(int pid, double damage, double lifetime) {
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

	public void setLifetime(double lifetime) {
		this.lifetime = lifetime;
	}

	@Override
	public Map<String, String> getParameters(){
		Map<String,String> res = new HashMap<>();
		res.put("Damage", Double.toString(damage));
		res.put("Lifetime", Double.toString(lifetime));
		
		return res;
	}
}
