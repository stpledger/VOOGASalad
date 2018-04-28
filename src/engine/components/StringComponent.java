package engine.components;

/**
 * Interface for a component that contains a string. This interface allows both reading and writing of the element's data.
 * @author fitzj
 *
 */
public interface StringComponent extends ReadStringComponent {
	public String getData();
	public void setData(String data);
	public int getPID();
	public void setPID(int pid);
}
