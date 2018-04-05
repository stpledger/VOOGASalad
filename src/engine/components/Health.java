package engine.components;

public class Health extends Component {
	private double health;
	
	
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
	
}
