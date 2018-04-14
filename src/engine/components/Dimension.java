package engine.components;


import java.util.HashMap;
import java.util.Map;

/**
 * This component defines dimensions of the sprite. It consists of height and width of the sprite.
 * It is instantiated with a path of image passed from authoring environment, and changes according to game logic.
 * @author Yameng
 */

public class Dimension extends Component {
	private double height,width;
	public static String KEY = "Dimension";
	
	public Dimension(int pid, double w, double h) {
		super(pid, KEY);
		this.height = h;
		this.width = w;
	}

	public double[] getDimension() {
		return new double[]{width,height};
	}
	
	public void setDimension(double x, double y) {
		this.width = x;
		this.height = y;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public static String getKey() {
		return KEY;
	}

	@Override
	public Map<String, String> getParameters(){
		Map<String,String> res = new HashMap<String, String>(){{
			put("Dimension X", Double.toString(width));
			put("Dimension Y", Double.toString(height));
		}};
		return res;
	}
}
