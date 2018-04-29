package engine.components;

public class Jumps extends SingleDataComponent{

	public static final String KEY = "Jumps";
	
	private int max;
	
	public Jumps(int pid, int max) {
		super(pid, max);
		this.max = max;
	}

	public void reset() {
		setData(max);
	}
	
	@Override
	public String getKey() {
		return KEY;
	}

	
	
}
