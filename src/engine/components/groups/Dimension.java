package engine.components.groups;

import engine.components.Component;
import engine.components.DataComponent;
import engine.components.Height;
import engine.components.Width;

public class Dimension implements Component {
	public static final String KEY = "Dimension";

	
	private DataComponent xa;
	private DataComponent ya;
	
	public Dimension(int pid, double xa, double ya) {
		this.xa = new Width(pid, xa);
		this.ya = new Height(pid, ya);
	}
	
	public double getWidth() {
		return xa.getData();
	}
	
	public double getHeight() {
		return ya.getData();
	}
	
	public int getPID() {
		return xa.getPID();
	}
	
	public String getKey() {
		return KEY;
	}
	
	public void setWidth(double x) {
		xa.setData(x);
	}
	
	public void setHeight(double y) {
		ya.setData(y);
	}
	
}
