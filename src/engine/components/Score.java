package engine.components;

/**
 * Data component representing an entity's score. Only used for player entities.
 * @author fitzj
 *
 */
public class Score extends SingleDataComponent {
	
	public static final String KEY = "Score";
	
	public Score(int pid, double data) {
		super(pid, data);
	}

	public String getKey() {
		return KEY;
	}

}
