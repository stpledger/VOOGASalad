package engine.components;

public class Player extends SingleDataComponent {

	public static final String KEY = "Player";
	
	public Player(int pid, int lives) {
		super(pid, lives);
	}

	public String getKey() {
		return KEY;
	}

}
