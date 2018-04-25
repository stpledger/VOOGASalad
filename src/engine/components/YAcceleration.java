package engine.components;

import java.util.HashMap;
import java.util.Map;

public class YAcceleration extends Component{

	public static String KEY = "YAcceleration";
	private double yAcc;

	public YAcceleration(int pid, double yAcc) {
		super(pid, KEY);
		this.yAcc=yAcc;
	}
	
	public void setyAcc(double y) {
		this.yAcc=y;
	}
	public double getyAcc() {
		return this.yAcc;
	}

	@Override
	public Map<String, String> getParameters() {
		Map<String,String> res = new HashMap<>();
		res.put("Acceleration Y", Double.toString(yAcc));	
		return res;
	}

}
