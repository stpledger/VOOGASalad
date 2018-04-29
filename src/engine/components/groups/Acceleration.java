package engine.components.groups;

import engine.components.Component;
import engine.components.DataComponent;
import engine.components.XAcceleration;
import engine.components.YAcceleration;

public class Acceleration implements Component {

	private DataComponent xa;
	private DataComponent ya;
	
	public static final String KEY = "Acceleration";
	
	public Acceleration(int pid, double xa, double ya) {
		this.xa = new XAcceleration(pid, xa);
		this.ya = new YAcceleration(pid, ya);
	}
	
	public double getXAcc() {
		return xa.getData();
	}
	
	public double getYAcc() {
		return ya.getData();
	}
	
	public int getPID() {
		return xa.getPID();
	}
	
	public String getKey() {
		return KEY;
	}
	
	public void setXAcc(double x) {
		xa.setData(x);
	}
	
	public void setYAcc(double y) {
		ya.setData(y);
	}
	
}
