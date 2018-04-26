package engine.components;

public interface DataComponent extends ReadDataComponent {
	public double getData();
	public void setData(double data);
	public int getPID();
	public void setPID(int pid);
	
}
