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
	 * @param xAcc		Initial x acceleration
	 * @param yAcc		Initial y acceleration
	 */
	public Acceleration(int pid, double xAcc, double yAcc) {
		super(pid);
		this.xAcc = xAcc;
		this.yAcc = yAcc;
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
		List<String[]> parameters = new ArrayList<String[]>(){{
		     add(new String[] {"xAcc","double"});
		     add(new String[] {"yAcc","double"});
		}};
		
		return parameters;
	}
}
