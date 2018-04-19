package engine.components;

public class Score extends SingleDataComponent {
	
	public Score(int pid, double data) {
		super(pid, data);
	}

	public static final String KEY = "Score";

	public String getKey() {
		return KEY;
	}
	
}
