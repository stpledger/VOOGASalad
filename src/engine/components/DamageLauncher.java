package engine.components;


import java.util.HashMap;
import java.util.Map;

/**
 * This component defines poisons points of the game object.
 * The entity with this component can harm other entities it collides with,
 * by reducing poisons points from the health points of others.
 * It is instantiated with an initial value passed from authoring environment,
 * and changes according to game logic.
 * @author Yameng
 */
public class DamageLauncher extends Component {
	private double damage;
	private double lifetime;
	public static String KEY = "DamageLauncher";


	public DamageLauncher (int pid, double damage, double lifetime) {
		super(pid);
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
	
	public String getKey() {
		return KEY;
	}


	@Override
	public Map<String, String> getParameters(){
		Map<String,String> res = new HashMap<>();
		res.put("Damage", Double.toString(damage));
		res.put("Lifetime", Double.toString(lifetime));
		
		return res;
	}
}
