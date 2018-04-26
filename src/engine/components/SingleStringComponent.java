package engine.components;

public abstract class SingleStringComponent implements Component, StringComponent, ReadStringComponent {

	private int pid;
	private String data;
	
	public SingleStringComponent(int pid, String data) {
		this.pid = pid;
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public int getPID() {
		return pid;
	}
	
	public void setPID(int pid) {
		this.pid = pid;
	}

}
