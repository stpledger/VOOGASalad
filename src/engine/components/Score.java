package engine.components;

import java.util.Map;

public class Score extends Component{
    public static String KEY ="Score";
    private double score;

	public Score(int pid) {
		super(pid, KEY);
		this.score=0;
	}
    
	public void addScore(double s) {
		score+=s;
	}
	
	public double getScore() {
		return this.score;
	}
	@Override
	public Map<String, String> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
