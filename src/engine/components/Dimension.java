package engine.components;


import java.util.ArrayList;
import java.util.List;

/**
 * This component defines dimensions of the sprite. It consists of height and width of the sprite.
 * It is instantiated with a path of image passed from authoring environment, and changes according to game logic.
 * @author Yameng
 */

public class Dimension extends Component{
	private double height,width;
	
	public Dimension(int pid, double w, double h) {
		super(pid);
		this.height = h;
		this.width = w;
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
}
