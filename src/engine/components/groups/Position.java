package engine.components.groups;

import engine.components.Component;
import engine.components.DataComponent;
import engine.components.XPosition;
import engine.components.YPosition;

public class Position implements Component {

	public static final String KEY = "Position";

	private DataComponent xa;
	private DataComponent ya;
	
	public Position(int pid, double xa, double ya) {
		this.xa = new XPosition(pid, xa);
		this.ya = new YPosition(pid, ya);
	}
	
	public double getXPos() {
		return xa.getData();
	}
	
	public double getYPos() {
		return ya.getData();
	}
	
	public int getPID() {
		return xa.getPID();
	}
	
	public String getKey() {
		return KEY;
	}
	
	public void setXPos(double x) {
		xa.setData(x);
	}
	
	public void setYPos(double y) {
		ya.setData(y);
	}
	
}
