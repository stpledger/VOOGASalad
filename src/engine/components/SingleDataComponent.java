package engine.components;

/**
 * Component containing a single double as data. Implements component interfaces for reading and read/writing.
 * @author fitzj
 *
 */
public abstract class SingleDataComponent implements DataComponent, ReadDataComponent, Component {
	
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

	public void setPID(int pid) {
		this.pid = pid;
	}
	
}
