package engine.components;


import java.util.ArrayList;
import java.util.List;

/**
 * Component for an entitie's health. Contains one double to represent this value.
 * @author fitzj
 */
public class Health extends Component {
	private double health;
	public static String KEY = "Health";
	
	
	public Health(int pid, double health) {
		super(pid, KEY);
		this.health = health;
	}
	
	public double getHealth() {
		return health;
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
<<<<<<< HEAD

=======
>>>>>>> 9d2c61e58bf633d7fda5043bafe04e74d489d2b8

}
