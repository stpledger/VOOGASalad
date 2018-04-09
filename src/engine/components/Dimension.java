package engine.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This component defines dimensions of the sprite. It consists of height and width of the sprite.
 * It is instantiated with a path of image passed from authoring environment, and changes according to game logic.
 * @author Yameng
 */

public class Dimension extends Component{
	private double height,width;
	
	public Dimension(int pid, List<String> parameters) {
		super(pid);
		this.height = Double.parseDouble(parameters.get(0));
		this.width = Double.parseDouble(parameters.get(1));
	}
	
	public static String getKey() {
		return "Dimension";
	}

	public double[] getDimension() {
		return new double[]{width,height};
	}
	
	public void setDimension(double[] dimension) {
		this.width = dimension[0];
		this.height = dimension[1];
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
	
	@Override
	public List<String[]> getParameters(){
		List<String[]> parameters = new ArrayList<String[]>(){{
		     add(new String[] {"width","double"});
		     add(new String[] {"height","double"});
		}};
		
		return parameters;
	}
}
