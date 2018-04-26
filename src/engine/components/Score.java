package engine.components;

public class Score extends SingleDataComponent {
	
	public Score(int pid, double data) {
		super(pid, data);
	}

<<<<<<< HEAD
public class Score extends Component{
    public static String KEY ="Score";
    private double score;
=======
	public static final String KEY = "Score";
>>>>>>> cbc99bc97a87701514f22041cc4b3d76a6a9d504

	public String getKey() {
		return KEY;
	}
<<<<<<< HEAD
    
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

=======
	
>>>>>>> cbc99bc97a87701514f22041cc4b3d76a6a9d504
}
