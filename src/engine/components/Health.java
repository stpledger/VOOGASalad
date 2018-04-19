package engine.components;


import java.util.HashMap;
import java.util.Map;

/**
 * Component for an entitie's health. Contains one double to represent this value.
 * @author fitzj
 */
public class Health extends Component {
	private double health;
	public static String KEY = "Health";
	
	public Health(int pid, double health) {
		super(pid);
		this.health = health;
	}
	
	public double getHealth() {
		return health;
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
	
	public String getKey() {
		return KEY;
	}


	@Override
	public Map<String, String> getParameters(){
		Map<String,String> res = new HashMap<>();
		res.put("Health", Double.toString(health));
		
		return res;
	}
}
