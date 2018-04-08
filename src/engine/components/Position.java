package engine.components;

import java.util.HashMap;
import java.util.Map;

/**
 * Component class for the position of an entity. Contains x and y coordinates as doubles.
 * 
 * @author fitzj
 */
public class Position extends Component {

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

	public static String getKey() {
		return "Position";
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
	
	@Override
	public Map<String, String> getParameters(){
		Map<String, String> map = new HashMap<>(){{
		     put("xPos", "double");
		     put("yPos", "double");
		}};
		
		return map;
	}
}
