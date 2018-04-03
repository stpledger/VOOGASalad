package engine.components;

public class Component {
	
	private int pid;
	
	public Component(int pid) {
		this.pid = pid;
	}
	
	public int getParentID () {
		return pid;
	}
	
}
