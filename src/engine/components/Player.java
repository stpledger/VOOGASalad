package engine.components;

public class Player extends FlagComponent {

	public static final String KEY = "Player";
	
	public Player(int pid) {
		super(pid);
	}

	public String getKey() {
		return KEY;
	}

}
