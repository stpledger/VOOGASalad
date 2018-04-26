package engine.components;

public abstract class SingleDataComponent implements DataComponent, ReadDataComponent{
	
	private int pid;
	private double data;
	
	public SingleDataComponent(int pid, double data) {
		this.pid = pid;
		this.data = data;
	}
	
	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}

	public int getPID() {
		return pid;
	}

	public void addData(double add){
		data += add;
	}

	public void setPID(int pid) {
		this.pid = pid;
	}
	
}
