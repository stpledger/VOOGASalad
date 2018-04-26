package engine.components;



public class Score extends SingleDataComponent {

	public Score(int pid, double data) {
		super(pid, data);
	}

	public void addScore(double s) {
		score+=s;
	}
	
	public double getScore() {
		return this.score;
	}
}
