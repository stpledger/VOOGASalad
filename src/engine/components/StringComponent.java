package engine.components;

public interface StringComponent extends ReadStringComponent {
	public String getData();
	public void setData(String data);
	public String getKey();
	public int getPID();
	public void setPID(int pid);
}
