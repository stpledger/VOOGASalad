package engine.components;
/**
 * This component is added to entities that when collided with player
 * either give or subtract certain amount of score
 * @sv116
 */
import java.util.Map;

public class ScoreLauncher extends Component{
    public static String KEY ="ScoreLauncher";
    private double score;

	public ScoreLauncher(int pid, double score) {
		super(pid, KEY);
		this.score=score;
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