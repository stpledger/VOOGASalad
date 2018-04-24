package engine.components;

import java.util.Map;

public class Score extends Component{
    private static String KEY ="Score";
    private double score;

	public Score(int pid) {
		super(pid, KEY);
		this.score=0;
	}
    
	
	
	@Override
	public Map<String, String> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
