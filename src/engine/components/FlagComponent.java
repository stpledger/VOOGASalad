package engine.components;

public abstract class FlagComponent implements Component {

	private int pid;
	
	public FlagComponent(int pid) {
		this.pid = pid;
	}
	
	public int getPID() {
		return pid;
	}
	
	public void setPID(int pid) {
		this.pid = pid;
	}
	
}
