package engine.components;

/**
 * Component class for the position of an entity. Contains x and y coordinates as doubles.
 * 
 * @author fitzj
 */
public class Position extends Component implements IComponent {

	private double xPos;
	private double yPos;
	
	/**
	 * Constructor for parent id, x, and y initial values
	 * @param pid	Parent id as an int
	 * @param xPos	Initial x position as a double
	 * @param yPos	Initial y position as a double
	 */
	public Position(int pid, double xPos, double yPos) {
		super(pid);
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
}
