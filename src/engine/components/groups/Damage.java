package engine.components.groups;

import engine.components.Component;
import engine.components.DamageLifetime;
import engine.components.DamageValue;
import engine.components.DataComponent;

public class Damage implements Component {

	public static final String KEY = "Damage";

	
	private DataComponent xa;
	private DataComponent ya;
	
	public Damage(int pid, double xa, double ya) {
		this.xa = new DamageValue(pid, xa);
		this.ya = new DamageLifetime(pid, ya);
	}
	
	public double getDamage() {
		return xa.getData();
	}
	
	public double getLifetime() {
		return ya.getData();
	}
	
	public int getPID() {
		return xa.getPID();
	}
	
	public String getKey() {
		return KEY;
	}
	
	public void setDamage(double x) {
		xa.setData(x);
	}
	
	public void setLifetime(double y) {
		ya.setData(y);
	}
	
}
