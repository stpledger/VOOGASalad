package engine.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public static String getKey() {
		return "Damage";
	}
	
	public Damage (int pid, List<String> parameters) {
		super(pid);
		this.damage = Double.parseDouble(parameters.get(0));
		this.lifetime = Double.parseDouble(parameters.get(1));
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

	@Override
	public List<String[]> getParameters(){
		List<String[]> parameters = new ArrayList<String[]>(){{
		     add(new String[] {"damage","double"});
		     add(new String[] {"lifetime","double"});
		}};
		
		return parameters;
	}
}
