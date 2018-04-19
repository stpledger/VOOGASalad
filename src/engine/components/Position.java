package engine.components;


import java.util.HashMap;
import java.util.Map;

/**
 * Component class for the position of an entity. Contains x and y coordinates as doubles.
 *
 * @author fitzj
 */
public class Position extends Component implements Cloneable {
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

	public String getKey() { return KEY; }


	@Override
	public Position clone() {
		try {
			return (Position)super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Cannot clone");
		}
		return null;
	}

	@Override
	public Map<String, String> getParameters(){
		Map<String,String> res = new HashMap<>();
		res.put("Position X", Double.toString(xPos));
		res.put("Position Y", Double.toString(yPos));
		
		return res;
	}

}
