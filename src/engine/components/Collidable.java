package engine.components;

public class Collidable extends FlagComponent{

	public static final String KEY = "Collidable";
	
	public Collidable(int pid) {
		super(pid);
	}

	@Override
	public String getKey() {
		return KEY;
	}

}
