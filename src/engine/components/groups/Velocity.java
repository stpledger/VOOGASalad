package engine.components.groups;

import engine.components.Component;
import engine.components.DataComponent;
import engine.components.XVelocity;
import engine.components.YVelocity;

public class Velocity implements Component {

	
	public static final String KEY = "Velocity";

	private DataComponent xa;
	private DataComponent ya;
	
	public Velocity(int pid, double xa, double ya) {
		this.xa = new XVelocity(pid, xa);
		this.ya = new YVelocity(pid, ya);
	}
	
	public double getXVel() {
		return xa.getData();
	}
	
	public double getYVel() {
		return ya.getData();
	}
	
	public int getPID() {
		return xa.getPID();
	}
	
	public String getKey() {
		return KEY;
	}
	
	public void setXVel(double x) {
		xa.setData(x);
	}
	
	public void setYVel(double y) {
		ya.setData(y);
	}
	
}
