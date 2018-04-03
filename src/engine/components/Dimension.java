package engine.components;

public class Dimension extends Component{
	private double height,width;
	
	public Dimension(int pid, double height, double width) {
		super(pid);
		this.height = height;
		this.width = width;
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
