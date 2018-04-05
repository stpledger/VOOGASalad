package engine.components;

public class Acceleration extends Component {

	private double xAcc, yAcc;
	
	public static String getKey() {
		return "Acceleration";
	}
	
	public Acceleration(int pid, double x, double y) {
		super(pid);
		this.xAcc = x;
		this.yAcc = y;
	}

	public double getxAcc() {
		return xAcc;
	}

	public void setxAcc(double xAcc) {
		this.xAcc = xAcc;
	}

	public double getyAcc() {
		return yAcc;
	}

	public void setyAcc(double yAcc) {
		this.yAcc = yAcc;
	}

}
