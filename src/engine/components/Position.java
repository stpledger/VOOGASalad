package engine.components;


import java.util.HashMap;
import java.util.Map;

/**
 * Component class for the position of an entity. Contains x and y coordinates as doubles.
 *
 * @author fitzj
 */
public class Position extends ShowableComponent {
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
		super(pid, KEY);
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

	@Override
	public Map<String, String> getParameters(){
		Map<String,String> res = new HashMap<String, String>(){{
			put("Position X", Double.toString(xPos));
			put("Position Y", Double.toString(yPos));
		}};
		return res;
	}

}
