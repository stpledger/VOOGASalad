package engine.components;

public interface DataComponent extends ReadDataComponent {

	public double getData();
	public void setData(double data);
	public String getKey();
	public int getPID();
	public void setPID(int pid);
	
}
