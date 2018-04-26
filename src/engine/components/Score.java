package engine.components;



public class Score extends SingleDataComponent {
	public static final String KEY = "Score";
	public Score(int pid, double data) {
		super(pid, data);
	}

	public void addData(double s) {
		addData(s);
	}

	public double getScore() {
		return getData();
	}
}
