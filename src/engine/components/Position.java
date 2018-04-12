package engine.components;




/**
 * Component class for the position of an entity. Contains x and y coordinates as doubles.
 *
 * @author fitzj
 */
public class Position extends Component {
	private double xPos;
	private double yPos;

	public static String KEY = "Position";

	/**
	 * Constructor for parent id, x, and y initial values
	 * @param pid	Parent id as an int
	 * @param x	Initial x position as a double
	 * @param y	Initial y position as a double
	 */
	public Position(int pid, double x, double y) {
		super(pid);
		this.xPos = x;
		this.yPos = y;
	}

	public double getXPos() {
		return xPos;
	}

	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	public double getYPos() {
		return yPos;
	}

	public void setYPos(double yPos) {
		this.yPos = yPos;
	}

	public static String getKey() { return KEY; }

}
