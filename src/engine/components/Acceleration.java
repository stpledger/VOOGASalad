package engine.components;

public class Acceleration extends Component implements IComponent {

	private double accX, accY;
	
	public Acceleration(int pid, double x, double y) {
		super(pid);
		this.accX = x;
		this.accY = y;
	}

	public double getAccX() {
		return accX;
	}

	public void setAccX(double accX) {
		this.accX = accX;
	}

	public double getAccY() {
		return accY;
	}

	public void setAccY(double accY) {
		this.accY = accY;
	}

}
