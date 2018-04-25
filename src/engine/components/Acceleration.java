package engine.components;

import java.util.Map;
import java.util.HashMap;

/**
 * Component housing acceleration information. Can be used to apply gravity, force, etc
 * @author Yameng
 * @author fitzj
 */
public class Acceleration extends Component {

public static String KEY = "XAcceleration";
	
	private double xAcc;
	
	/**
	 * Constructs component with initial values and parent entity ID
	 * @param pid	Parent ID
	 * @param xAcc		Initial x acceleration
	 * @param yAcc		Initial y acceleration
	 */
	public Acceleration(int pid, double xAcc) {
		super(pid, KEY);
		this.xAcc = xAcc;
	}

	public double getxAcc() {
		return xAcc;
	}

	public void setxAcc(double xAcc) {
		this.xAcc = xAcc;
	}

	

	@Override
	public Map<String, String> getParameters(){
		Map<String,String> res = new HashMap<>();
		res.put("Acceleration X", Double.toString(xAcc));
		return res;
	}
}


