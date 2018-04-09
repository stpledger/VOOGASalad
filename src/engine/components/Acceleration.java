package engine.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Component housing acceleration information. Can be used to apply gravity, force, etc
 * @author fitzj
 */
public class Acceleration extends Component {

	public static String KEY = "Acceleration";
	
	private double xAcc, yAcc;
	
	/**
	 * Static method to get key for this component
	 * @return	Acceleration key
	 */
	public static String getKey() {
		return KEY;
	}
	
	/**
	 * Constructs component with initial values and parent entity ID
	 * @param pid	Parent ID
	 * @param x		Initial x acceleration
	 * @param y		Initial y acceleration
	 */
	public Acceleration(int pid, List<String> parameters) {
		super(pid);
		this.xAcc = Double.parseDouble(parameters.get(0));
		this.yAcc = Double.parseDouble(parameters.get(1));
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

	@Override
	public List<String[]> getParameters(){
		List<String[]> parameters = new ArrayList<>(){{
		     add(new String[] {"xAcc","double"});
		     add(new String[] {"yAcc","double"});
		}};
		
		return parameters;
	}
}
