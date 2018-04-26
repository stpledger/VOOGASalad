package engine.components;
/**
 * This component is added to entities that when collided with player
 * either give or subtract certain amount of score
 * @sv116
 */
import java.util.Map;

public class ScoreLauncher implements Component{
    public static String KEY ="ScoreLauncher";
    private double score;
	private int pid;

	public ScoreLauncher(int pid, double score) {
		this.pid = pid;
		this.score=score;
	}
    
	public void addScore(double s) {
		score+=s;
	}
	
	public double getScore() {
		return this.score;
	}

	public int getPID(){
		return pid;
	}
}
