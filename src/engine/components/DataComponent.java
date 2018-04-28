package engine.components;

/**
 * Interface for a component that contains a double. This interface allows both reading and writing of the element's data.
 * @author fitzj
 *
 */
public interface DataComponent extends ReadDataComponent {
	public double getData();
	public void setData(double data);
	public int getPID();
	public void setPID(int pid);	
}
